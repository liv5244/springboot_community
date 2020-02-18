package com.itliv.community.service.impl;

import com.itliv.community.dto.AccessTokenDTO;
import com.itliv.community.dto.GithubUser;
import com.itliv.community.mapper.UserMapper;
import com.itliv.community.model.User;
import com.itliv.community.provider.GithubProvider;
import com.itliv.community.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github,redirect.uri}")
    private String redirectUri;
    @Value(("${github.client.secret}"))
    private String clientSecret;
    @Value(("${github.client.id}"))
    private String clientId;

    @Override
    public void insertUser(User user) {
        log.info("serviceImpl执行了，并且得到user为" + user);
        userMapper.insertSelective(user);
    }

    /**
     * 根据/callback 中的返回的code和state来存储用户
     */
    public String insertUserByCodeAndState(String code, String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setState(state);
        accessTokenDTO.setCode(code);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null) {
            //判断数据库中是否已经存在了登陆记录，如果存在登陆记录，直接判断token
            //否则应该存入数据库，并且更新用户信息
            String accountId = String.valueOf(githubUser.getId());
            User user = findUserbyAccountId(accountId);
            String token = UUID.randomUUID().toString();
            if (user == null) {
                user = new User();
                user.setToken(token);
                user.setGmtModified(String.valueOf(System.currentTimeMillis()));
                user.setName(githubUser.getLogin());
                user.setAvatarUrl(githubUser.getAvatarUrl());
                user.setAccountId(accountId);
                user.setGmtCreate(String.valueOf(System.currentTimeMillis()));
                insertUser(user);
            } else {
                user.setToken(token);
                user.setGmtModified(String.valueOf(System.currentTimeMillis()));
                user.setName(githubUser.getLogin());
                user.setAvatarUrl(githubUser.getAvatarUrl());
                updateUser(user);
            }

            log.info("登陆成功，login user：" + user);
            return token;
        }
        return null;
    }

    @Override
    public User findUserbyAccountId(String accountId) {
        return userMapper.findUserByAccountId(accountId);
    }

    @Override
    public void updateUser(User user) {
        System.out.println("更新了用户的下列信息:" + user);
        System.out.println(user);
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public List<User> findUserByToken(String token) {
        List<User> users = userMapper.findUserByToken(token);
        log.info("findByToken:"+token);
        return users;
    }
}

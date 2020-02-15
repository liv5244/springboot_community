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
        userMapper.insertUser(user);
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
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setName(githubUser.getLogin());
            insertUser(user);
            log.info("登陆成功，login user：" + user);
            return token;
        }
        return null;
    }

    @Override
    public List<User> findUserByToken(String token) {
        log.info("service 中的findUserByToken 执行了...");
        List<User> users = userMapper.findUserByToken(token);
        return users;
    }
}

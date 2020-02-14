package com.itliv.community.service.impl;

import com.itliv.community.mapper.UserMapper;
import com.itliv.community.model.User;
import com.itliv.community.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insertUser(User user) {
        log.info("serviceImpl执行了，并且得到user为"+user);
        userMapper.insertUser(user);
    }
}

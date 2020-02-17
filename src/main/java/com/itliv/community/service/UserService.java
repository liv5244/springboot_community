package com.itliv.community.service;

import com.itliv.community.model.User;

import java.util.List;

public interface UserService {
    /**
     * 向数据库中插入user
     * @param user
     */
    void insertUser(User user);

    User findUserbyAccountId(String accountId);

    void updateUser(User user);

    /**
     * 根据token查找用户
     * @param token
     * @return
     */
    List<User> findUserByToken(String token);
}

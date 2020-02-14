package com.itliv.community.service;

import com.itliv.community.model.User;

public interface UserService {
    /**
     * 向数据库中插入user
     * @param user
     */
    public void insertUser(User user);

}

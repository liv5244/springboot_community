package com.itliv.community;

import com.itliv.community.mapper.UserMapper;
import com.itliv.community.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootCommunityApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        User  user = new User();
        user.setAccountId("111111");
        user.setGmtCreate(String.valueOf(123L));
        user.setGmtModified(String.valueOf(456L));
        user.setName("liwei");
        user.setToken("asdf2131");
        userMapper.insert(user);
    }

}

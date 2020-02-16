package com.itliv.community.utils;

import com.itliv.community.model.User;
import com.itliv.community.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户的处理工具
 */
@Component
public class UserUtils {

    @Autowired
    UserServiceImpl userService;

    public User checkCookieAndReturnUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        User user = null;
        if (cookies != null) {
            for (Cookie cookie :
                    cookies) {
                if ("token".equals(cookie.getName())) {
                    //找到了token的值，将数据库中的token和cookie中的token进行比较
                    String token = cookie.getValue();
                    List<User> users = userService.findUserByToken(token);
                    if (users != null && users.size() == 1) {
                        user = users.get(0);
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        return user;
    }
}

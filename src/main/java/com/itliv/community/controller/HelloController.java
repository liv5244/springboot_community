package com.itliv.community.controller;

import com.itliv.community.model.User;
import com.itliv.community.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Slf4j
public class HelloController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/")
    public String toIndex(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie :
                cookies) {
            if ("token".equals(cookie.getName())) {
                //找到了token的值，将数据库中的token和cookie中的token进行比较
                String token = cookie.getValue();
                List<User> users = userService.findUserByToken(token);
                if (users != null && users.size() == 1) {
                    request.getSession().setAttribute("user", users.get(0));
                }
                break;
            }
        }
        log.info("toindex  controller 执行了...");
        return "index";
    }
}

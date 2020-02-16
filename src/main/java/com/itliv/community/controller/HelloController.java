package com.itliv.community.controller;

import com.itliv.community.model.User;
import com.itliv.community.service.impl.UserServiceImpl;
import com.itliv.community.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@Slf4j
public class HelloController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserUtils userUtils;

    @GetMapping("/")
    public String toIndex(HttpServletRequest request) {
        User user = userUtils.checkCookieAndReturnUser(request);
        if (user != null) {
            Date now = new Date();
            log.info(user.getName() + "在时间：" + now +
                    "登陆了首页....");
        }
        return "index";
    }
}

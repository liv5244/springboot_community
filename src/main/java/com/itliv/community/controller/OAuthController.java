package com.itliv.community.controller;

import com.itliv.community.provider.GithubProvider;
import com.itliv.community.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class OAuthController {

    @Autowired
    private UserServiceImpl userService;

    /**
     * 调用github登陆接口的回调
     * @param code
     * @param state
     * @param response
     * @return
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        String token = userService.insertUserByCodeAndState(code, state);
        if (token != null) {
            //写入cookie和sesion
            response.addCookie(new Cookie("token", token));
            return "redirect:/index";
        } else {
            return "redirect:/index";
        }
    }
}

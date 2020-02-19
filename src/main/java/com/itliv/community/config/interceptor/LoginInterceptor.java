package com.itliv.community.config.interceptor;

import com.itliv.community.model.User;
import com.itliv.community.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    UserServiceImpl userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
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
                        return true;
                    }
                }
            }
        }
//        request.getRequestDispatcher("/info").forward(request, response);
        System.out.println("出现了登录问题");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

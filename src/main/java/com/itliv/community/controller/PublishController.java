package com.itliv.community.controller;

import com.itliv.community.model.Question;
import com.itliv.community.model.User;
import com.itliv.community.service.impl.QuestionServiceImpl;
import com.itliv.community.service.impl.UserServiceImpl;
import com.itliv.community.utils.Msg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@Slf4j
public class PublishController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    QuestionServiceImpl questionService;

    private User user;

    @GetMapping("/publish")
    public String publish(HttpServletRequest request) {
        user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Date now = new Date();
            log.info(user.getName() + "在时间：" + now +
                    "登陆了publish页....");
        }
        return "publish";
    }

    @PostMapping("/publish")
    @ResponseBody
    public Msg sendQuestion(HttpServletRequest request) {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String tag = request.getParameter("tag");
        if (user != null) {
            questionService.insertQuestion(new Question(null, title, content, System.currentTimeMillis(), System.currentTimeMillis(), user.getId(),0,0,0,0,tag));
            log.info("id 为：" + user.getAccountId() + ",name 为：" + user.getName() + "提交了问题：title-" + title + ",content-" + content + ",tag-" + tag);
            return Msg.success();
        } else {
            return Msg.fail();
        }
    }
}

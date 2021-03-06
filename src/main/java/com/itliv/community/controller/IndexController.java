package com.itliv.community.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.itliv.community.dto.QuestionDTO;
import com.itliv.community.model.Notification;
import com.itliv.community.model.Question;
import com.itliv.community.model.User;
import com.itliv.community.service.NotificationService;
import com.itliv.community.service.impl.NotificationServiceImpl;
import com.itliv.community.service.impl.QuestionServiceImpl;
import com.itliv.community.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
public class IndexController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    QuestionServiceImpl questionService;
    @Autowired
    NotificationServiceImpl notificationService;

    @GetMapping("/")
    public String forward() {
        return "redirect:/index";
    }

    @GetMapping(value = {"/index"})
    public String toIndex(@RequestParam(value = "page",defaultValue = "1") int page,
            HttpServletRequest request, Model model) {
        //判断登录状态的
        User user = (User) request.getSession().getAttribute("user");
        Integer receiverId = null;
        if (user != null) {
            Date now = new Date();
            log.info(user.getName() + "在时间：" + now +
                    "登陆了首页....");
            receiverId = user.getId();
        }
        //从数据库读取问题展示在前台界面
        Page<QuestionDTO> lists = questionService.findQuesWithUserByPage(page, 10);
        PageInfo<QuestionDTO> pageInfo = new PageInfo<>(lists);
//        log.info("首页分页信息："+pageInfo.toString());
//        log.info("首页分页信息："+ JSON.toJSONString(pageInfo));
        List<Question> hotQuestions = questionService.findHotQuestions();
        model.addAttribute("lists", pageInfo);
        model.addAttribute("hotQues",hotQuestions);
        if (receiverId != null) {
            int unreadCount = notificationService.selectCountByReceiverId(receiverId);
            model.addAttribute("unreadCount",unreadCount);
        }
        return "index";
    }

}

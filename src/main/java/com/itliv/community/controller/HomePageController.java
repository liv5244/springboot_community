package com.itliv.community.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.itliv.community.dto.NotificationDTO;
import com.itliv.community.dto.QuestionDTO;
import com.itliv.community.enums.NotificationStatusEnum;
import com.itliv.community.exception.CustomizeErrorCode;
import com.itliv.community.exception.CustomizeException;
import com.itliv.community.model.Comment;
import com.itliv.community.model.Notification;
import com.itliv.community.model.Question;
import com.itliv.community.model.User;
import com.itliv.community.service.QuestionService;
import com.itliv.community.service.impl.CommentServiceImpl;
import com.itliv.community.service.impl.NotificationServiceImpl;
import com.itliv.community.service.impl.QuestionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Slf4j
public class HomePageController {

    @Autowired
    QuestionServiceImpl questionService;

    @Autowired
    NotificationServiceImpl notificationService;

    @Autowired
    CommentServiceImpl commentService;

    private long quesCount;

    @GetMapping("/homepage")
    public String toHomePage(@RequestParam(value = "page", defaultValue = "1") int page,
                             HttpServletRequest request,
                             Model model) {
        User user = (User) request.getSession().getAttribute("user");
        Integer receiverId = null;
        if (user == null) {
            throw new CustomizeException(CustomizeErrorCode.USER_NOT_LOGIN);
        }
        receiverId = user.getId();
        //分页信息
        Page<QuestionDTO> lists = questionService.findMyQuesByIdForPage(page, 10, user.getId());
        PageInfo<QuestionDTO> pageInfo = new PageInfo<>(lists);
        model.addAttribute("lists", pageInfo);
        quesCount = pageInfo.getTotal();
        //未读通知
        if (receiverId != null) {
            int unreadCount = notificationService.selectCountByReceiverId(receiverId);
            model.addAttribute("unreadCount",unreadCount);
        }
        return "homepage";
    }

    @GetMapping("/homepage/notification")
    public String toNotificationPage(@RequestParam(value = "page",defaultValue = "1") int page,
                                     HttpServletRequest request,
                                     Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomizeException(CustomizeErrorCode.USER_NOT_LOGIN);
        }
        int receiverId = user.getId();
        Page<NotificationDTO> notifications = (Page<NotificationDTO>) notificationService.selectByReceiver(receiverId, NotificationStatusEnum.NOREAD.getStatus(),page,10);

        PageInfo<NotificationDTO> pageInfo = new PageInfo<>(notifications);

        log.info(JSON.toJSONString(pageInfo));
        model.addAttribute("notifications", pageInfo);
        model.addAttribute("unreadCount", notifications.size());
        model.addAttribute("quesCount",quesCount);
        return "hp_notification";
    }

    @GetMapping("/homepage/notification/2ques")
    public String note2qus(@RequestParam(value = "id", defaultValue = "-1") int id,
                           @RequestParam(value = "dirQ", defaultValue = "-1") int dirQ) {
        //设置已读并重定向问题
        Notification notification = new Notification();
        notification.setId(id);
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationService.updateByPrimaryKeySelective(notification);
        return "redirect:/question/info/"+dirQ;
    }

    @GetMapping("/homepage/notification/2com")
    public String note2com(@RequestParam(value = "id", defaultValue = "-1") int id,
                           @RequestParam(value = "dirC", defaultValue = "-1") int dirC) {
        //设置已读并重定向评论所在的问题
        Notification notification = new Notification();
        notification.setId(id);
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationService.updateByPrimaryKeySelective(notification);
        Comment comment = commentService.findCommentById(dirC);
        int dirQ = comment.getParentId();
        return "redirect:/question/info/"+dirQ;
    }
}

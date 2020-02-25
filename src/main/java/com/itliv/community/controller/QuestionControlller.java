package com.itliv.community.controller;

import com.itliv.community.dto.CommentDTO2;
import com.itliv.community.dto.QuestionDTO;
import com.itliv.community.enums.CommentTypeEnum;
import com.itliv.community.exception.CustomizeErrorCode;
import com.itliv.community.exception.CustomizeException;
import com.itliv.community.model.Question;
import com.itliv.community.model.User;
import com.itliv.community.service.impl.CommentServiceImpl;
import com.itliv.community.service.impl.NotificationServiceImpl;
import com.itliv.community.service.impl.QuestionServiceImpl;
import com.itliv.community.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionControlller {

    @Autowired
    QuestionServiceImpl questionService;

    @Autowired
    CommentServiceImpl commentService;

    @Autowired
    NotificationServiceImpl notificationService;

    @RequestMapping("/info/{id}")
    public String showQues(@PathVariable("id") int id, Model model,
                           HttpServletRequest request) {
        User user = checkLogin(request);
        QuestionDTO question = questionService.findQuesById(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        questionService.incViewCount(id);
        //展示评论
        List<CommentDTO2> comments = commentService.findCommentByParentId(id, CommentTypeEnum.QUESTION.getTypeCode());
        List<Question> relatedQuestions = questionService.findRelatedQuestions(question);
        model.addAttribute("ques", question);
        model.addAttribute("comments", comments);
        model.addAttribute("relatedQues", relatedQuestions);
        model.addAttribute("self", false);
        //未读通知
        Integer receiverId = user.getId();
        if (receiverId != null) {
            int unreadCount = notificationService.selectCountByReceiverId(receiverId);
            model.addAttribute("unreadCount",unreadCount);
        }
        return "question";
    }

    @RequestMapping("/my/info/{id}")
    public String showMyQues(@PathVariable("id") int id, Model model,
                             HttpServletRequest request) {
        User user = checkLogin(request);
        QuestionDTO question = questionService.findQuesById(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        questionService.incViewCount(id);
        //展示评论
        List<CommentDTO2> comments = commentService.findCommentByParentId(id, CommentTypeEnum.QUESTION.getTypeCode());
        List<Question> relatedQuestions = questionService.findRelatedQuestions(question);
        model.addAttribute("ques", question);
        model.addAttribute("comments", comments);
        model.addAttribute("relatedQues", relatedQuestions);
        model.addAttribute("self", true);
        //未读通知
        Integer receiverId = user.getId();
        if (receiverId != null) {
            int unreadCount = notificationService.selectCountByReceiverId(receiverId);
            model.addAttribute("unreadCount",unreadCount);
        }
        return "question";
    }

    @PostMapping("/del")
    @ResponseBody
    public Msg delQues(@RequestParam("id") int id) {
        questionService.delQuesById(id);
        return Msg.success();
    }

    private User checkLogin(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomizeException(CustomizeErrorCode.USER_NOT_LOGIN);
        }
        return user;
    }
}

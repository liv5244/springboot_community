package com.itliv.community.controller;

import com.itliv.community.dto.QuestionDTO;
import com.itliv.community.dto.Tag;
import com.itliv.community.exception.CustomizeErrorCode;
import com.itliv.community.exception.CustomizeException;
import com.itliv.community.model.Question;
import com.itliv.community.model.User;
import com.itliv.community.service.impl.NotificationServiceImpl;
import com.itliv.community.service.impl.QuestionServiceImpl;
import com.itliv.community.service.impl.UserServiceImpl;
import com.itliv.community.utils.Msg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    NotificationServiceImpl notificationService;

    /**
     * 登录进入publish页
     *
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/publish")
    public String publish(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        Integer receiverId = null;
        if (user != null) {
            Date now = new Date();
            log.info(user.getName() + "在时间：" + now +
                    "登陆了publish页....");
            receiverId = user.getId();
        }
        model.addAttribute("ques", new Question());
        model.addAttribute("flag", "release");
        model.addAttribute("tags", Tag.getTags());
        if (receiverId != null) {
            int unreadCount = notificationService.selectCountByReceiverId(receiverId);
            model.addAttribute("unreadCount", unreadCount);
        }
        return "publish";
    }

    /**
     * 判断是发布还是修改，然后进行更新数据库操作
     *
     * @param request
     * @return
     */
    @PostMapping("/publish")
    @ResponseBody
    public Msg sendQuestion(HttpServletRequest request) {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String tag = request.getParameter("tag");
        String flag = request.getParameter("flag");
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            if ("release".equals(flag)) {
                Question question = new Question(null, title, content, System.currentTimeMillis(), System.currentTimeMillis(), user.getId(), 0, 0, 0, 0, tag);
                questionService.insertQuestion(question);
            }
            if ("edit".equals(flag)) {
                String id = request.getParameter("id");
                Question question = new Question(Integer.parseInt(id), title, content, System.currentTimeMillis(), System.currentTimeMillis(), user.getId(), 0, 0, 0, 0, tag);
                questionService.upadteQuestion(question);
            }
            log.info("id 为：" + user.getAccountId() + ",name 为： " + user.getName() + flag + " 了问题：title-" + title + ",content-" + content + ",tag-" + tag);
            return Msg.success();
        } else {
            return Msg.fail();
        }
    }

    /**
     * 修改问题界面
     *
     * @param id
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/publish/{id}")
    public String editQues(@PathVariable("id") int id, Model model,
                           HttpServletRequest request) {
        QuestionDTO quesById = questionService.findQuesById(id);
        if (quesById == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        User user = (User) request.getSession().getAttribute("user");
        if (!quesById.getUser().getAccountId().equals(user.getAccountId())) {
            //没有权限修改该问题
            throw new CustomizeException(CustomizeErrorCode.PERMISSION_DENIED);
        }

        model.addAttribute("ques", quesById);
        model.addAttribute("flag", "edit");
        model.addAttribute("tags", Tag.getTags());
        if (user.getId() != null) {
            int unreadCount = notificationService.selectCountByReceiverId(user.getId());
            model.addAttribute("unreadCount",unreadCount);
        }

        return "publish";
    }
}

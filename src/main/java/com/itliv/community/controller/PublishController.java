package com.itliv.community.controller;

import com.itliv.community.dto.QuestionDTO;
import com.itliv.community.exception.CustomizeErrorCode;
import com.itliv.community.exception.CustomizeException;
import com.itliv.community.model.Question;
import com.itliv.community.model.User;
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

    @GetMapping("/publish")
    public String publish(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Date now = new Date();
            log.info(user.getName() + "在时间：" + now +
                    "登陆了publish页....");
        }
        model.addAttribute("ques", new Question());
        model.addAttribute("flag", "release");
        return "publish";
    }

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
        return "publish";
    }
}

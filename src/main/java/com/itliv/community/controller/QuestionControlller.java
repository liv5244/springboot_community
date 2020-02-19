package com.itliv.community.controller;

import com.itliv.community.dto.QuestionDTO;
import com.itliv.community.exception.CustomizeErrorCode;
import com.itliv.community.exception.CustomizeException;
import com.itliv.community.exception.ICustomizeErrorCode;
import com.itliv.community.service.impl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/question")
public class QuestionControlller {

    @Autowired
    QuestionServiceImpl questionService;

    @RequestMapping("/info/{id}")
    public String showQues(@PathVariable("id") int id, Model model) {
        QuestionDTO question = questionService.findQuesById(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        model.addAttribute("ques", question);
        model.addAttribute("self",false);
        return "question";
    }

    @RequestMapping("/my/info/{id}")
    public String showMyQues(@PathVariable("id") int id, Model model) {
        QuestionDTO question = questionService.findQuesById(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        model.addAttribute("ques", question);
        return "question";
    }
}

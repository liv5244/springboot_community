package com.itliv.community.controller;

import com.itliv.community.dto.QuestionDTO;
import com.itliv.community.model.Question;
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
        model.addAttribute("ques", question);
        return "question";
    }
}

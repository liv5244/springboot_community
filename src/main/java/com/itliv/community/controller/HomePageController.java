package com.itliv.community.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.itliv.community.model.Question;
import com.itliv.community.model.User;
import com.itliv.community.service.QuestionService;
import com.itliv.community.service.impl.QuestionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class HomePageController {

    @Autowired
    QuestionServiceImpl questionService;

    @GetMapping("/homepage")
    public String toHomePage(@RequestParam(value = "page", defaultValue = "1") int page,
                             HttpServletRequest request,
                             Model model) {
        User user = (User) request.getSession().getAttribute("user");
        Page<Question> lists = questionService.findMyQuesByIdForPage(page, 10, user.getId());
        PageInfo<Question> pageInfo = new PageInfo<>(lists);
        log.info("pageInfo:" + JSON.toJSONString(pageInfo));
        model.addAttribute("lists", pageInfo);
        return "homepage";
    }

}

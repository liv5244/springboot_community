package com.itliv.community.service.impl;

import com.itliv.community.mapper.QuestionMapper;
import com.itliv.community.model.Question;
import com.itliv.community.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionMapper questionMapper;

    @Override
    public void insertQuestion(Question question) {
        log.info("执行了插入语句,插入的question："+question);
        questionMapper.insertQuestion(question);
    }
}

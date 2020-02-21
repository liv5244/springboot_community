package com.itliv.community.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itliv.community.dto.QuestionDTO;
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
        log.info("执行了插入语句,插入的question：" + question);
        questionMapper.insertSelective(question);
    }

    @Override
    public Page<QuestionDTO> findQuesWithUserByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return questionMapper.findQuesWithUserByPage();
    }

    @Override
    public Page<QuestionDTO> findMyQuesByIdForPage(int pageNum, int pageSize, int creator) {
        PageHelper.startPage(pageNum, pageSize);
        return questionMapper.findMyQuesByIdForPage(creator);
    }

    @Override
    public QuestionDTO findQuesById(int id) {
        return questionMapper.findQuesByIdWithUser(id);
    }

    @Override
    public void upadteQuestion(Question question) {
        questionMapper.updateByPrimaryKeySelective(question);
    }

    @Override
    public void incViewCount(int id) {
        Question ques = new Question();
        ques.setId(id);
        ques.setViewCount(1);
        questionMapper.incViewCount(ques);
    }

    @Override
    public void incCommentCount(int id) {
        Question question = new Question();
        question.setId(id);
        question.setCommentCount(1);
        questionMapper.incCommentCount(question);
    }

    @Override
    public void delQuesById(int id) {
        questionMapper.deleteByPrimaryKey(id);
    }
}

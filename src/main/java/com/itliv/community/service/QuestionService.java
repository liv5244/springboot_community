package com.itliv.community.service;

import com.itliv.community.model.Question;

public interface QuestionService {
    /**
     * 插入问题
     * @param question
     */
    void insertQuestion(Question question);
}

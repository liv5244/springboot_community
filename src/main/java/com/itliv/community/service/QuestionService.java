package com.itliv.community.service;

import com.github.pagehelper.Page;
import com.itliv.community.model.Question;

import java.util.List;

public interface QuestionService {
    /**
     * 插入问题
     * @param question
     */
    void insertQuestion(Question question);

    /**
     * 查找questions并且携带user信息（多表查询）
     * @return
     */
    public Page<Question> findQuesWithUserByPage(int pageNum, int pageSize);
}

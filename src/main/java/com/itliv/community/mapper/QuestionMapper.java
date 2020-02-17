package com.itliv.community.mapper;

import com.github.pagehelper.Page;
import com.itliv.community.model.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {

    void insertQuestion(Question question);

    /**
     * 默认：这是按照发帖时间的倒叙排序的
     * @return
     */
    Page<Question> findQuesWithUserByPage();
}

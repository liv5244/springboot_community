package com.itliv.community.mapper;

import com.itliv.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title,content,gmt_create,gmt_modified,creator,tag) values (#{title},#{content},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void insertQuestion(Question question);
}

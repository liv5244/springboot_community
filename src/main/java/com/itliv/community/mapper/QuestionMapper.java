package com.itliv.community.mapper;

import com.github.pagehelper.Page;
import com.itliv.community.dto.QuestionDTO;
import com.itliv.community.model.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);

    /**
     * 默认：这是按照发帖时间的倒叙排序的
     * @return
     */
    Page<QuestionDTO> findQuesWithUserByPage();

    /**
     * 根据id查询用户的问题
     */
    Page<QuestionDTO> findMyQuesByIdForPage(int creator);

    QuestionDTO findQuesByIdWithUser(int id);

    void incViewCount(Question ques);

    void incCommentCount(Question question);
}
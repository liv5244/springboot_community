package com.itliv.community.mapper;

import com.github.pagehelper.Page;
import com.itliv.community.model.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HomePageMapper {
    /**
     * 根据id查询用户的问题
     */
    Page<Question> findMyQuesByIdForPage(int creator);
}

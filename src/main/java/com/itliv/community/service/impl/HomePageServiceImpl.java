package com.itliv.community.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itliv.community.mapper.HomePageMapper;
import com.itliv.community.model.Question;
import com.itliv.community.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomePageServiceImpl implements HomePageService {

    @Autowired
    private HomePageMapper homePageMapper;

    @Override
    public Page<Question> findMyQuesByIdForPage(int pageNum, int pageSize, int creator) {
        PageHelper.startPage(pageNum, pageSize);
        return homePageMapper.findMyQuesByIdForPage(creator);
    }
}

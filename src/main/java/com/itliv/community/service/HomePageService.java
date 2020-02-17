package com.itliv.community.service;

import com.github.pagehelper.Page;
import com.itliv.community.model.Question;

public interface HomePageService {

    Page<Question> findMyQuesByIdForPage(int pageNum, int pageSize, int creator);
}

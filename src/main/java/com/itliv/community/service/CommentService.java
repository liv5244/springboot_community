package com.itliv.community.service;

import com.itliv.community.dto.CommentDTO2;
import com.itliv.community.model.Comment;

import java.util.List;

public interface CommentService {

    void insert(Comment record);

    List<CommentDTO2> findCommentByParentId(int id, int parentId);

    void updateLike(int id, int liker);
}

package com.itliv.community.service.impl;

import com.itliv.community.dto.CommentCountDTO;
import com.itliv.community.dto.CommentDTO2;
import com.itliv.community.enums.CommentTypeEnum;
import com.itliv.community.exception.CustomizeErrorCode;
import com.itliv.community.exception.CustomizeException;
import com.itliv.community.mapper.CommentMapper;
import com.itliv.community.mapper.QuestionMapper;
import com.itliv.community.model.Comment;
import com.itliv.community.model.Question;
import com.itliv.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    QuestionServiceImpl questionService;

    @Override
    //用@Transactional用来处理事务，如果方法中任何一个事务出现了异常，方法则执行错误。事务会回滚
    @Transactional
    public void insert(Comment record) {
        if (record.getType().equals(CommentTypeEnum.COMMENT.getTypeCode())) {
            //回复评论
            Integer parentId = record.getParentId();
            Comment comment = commentMapper.selectByPrimaryKey(parentId);
            if (comment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
        } else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(record.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            questionService.incCommentCount(record.getParentId());
        }
        commentMapper.insert(record);
    }

    @Override
    public List<CommentDTO2> findCommentByParentId(int id, int parentId) {
        return commentMapper.selectByParentId(id, parentId);
    }

    @Override
    public void updateLike(int id, int liker) {
        Comment record = new Comment();
        record.setId(id);
        Comment comment = commentMapper.selectByPrimaryKey(id);
        if (comment.getLiker() != null && comment.getLiker().contains(liker+",")) {
            String old = liker + ",";
            record.setLiker(comment.getLiker().replace(old,""));
            record.setLikeCount(-1L);
        } else {
            record.setLiker(liker+",");
            record.setLikeCount(1L);
        }

        commentMapper.updateByPrimaryKeySelective(record);
    }

}

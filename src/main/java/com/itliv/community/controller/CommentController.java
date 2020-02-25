package com.itliv.community.controller;

import com.itliv.community.dto.CommentDTO;
import com.itliv.community.dto.CommentDTO2;
import com.itliv.community.enums.CommentTypeEnum;
import com.itliv.community.exception.CustomizeErrorCode;
import com.itliv.community.model.Comment;
import com.itliv.community.model.User;
import com.itliv.community.service.impl.CommentServiceImpl;
import com.itliv.community.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    CommentServiceImpl commentService;

    @PostMapping("/comment")
    @ResponseBody
    public Msg post(@RequestBody CommentDTO commentDTO,
                    HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return Msg.fail(CustomizeErrorCode.USER_NOT_LOGIN);
        }
        Integer parentId = commentDTO.getParentId();
        if (parentId == null || parentId == 0) {
            return Msg.fail(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        Integer type = commentDTO.getType();
        if (type == null || !CommentTypeEnum.isExist(type)) {
            return Msg.fail(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        Comment comment = new Comment();
        comment.setCommentor(user.getId());
        comment.setContent(commentDTO.getContent());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setLikeCount(0L);
        comment.setParentId(parentId);
        comment.setType(type);
        commentService.insert(comment);
        return Msg.success().extend("comment", comment);
    }

    @GetMapping("/comment/com2com")
    @ResponseBody
    public Msg post2Com(@RequestParam("parentId") int parentId,
                        @RequestParam("type") int type) {
        List<CommentDTO2> comments = commentService.findCommentByParentId(parentId, type);
        return Msg.success().extend("res", comments);
    }

    @GetMapping("/comment/like")
    @ResponseBody
    public Msg likeResolve(@RequestParam("id") int id,
                           HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return Msg.fail(CustomizeErrorCode.USER_NOT_LOGIN);
        }
        int liker = user.getId();
        commentService.updateLike(id,liker);
        return Msg.success();
    }

}

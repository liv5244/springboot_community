package com.itliv.community.dto;

import com.itliv.community.model.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class CommentDTO2 implements Serializable {
    private Integer id;

    /**
     * 如果type是1，则说明是回复问题，那么parent_id就是问题的id；
     如果type是2，则说明是回复评论，那么parent_id就是评论的id；
     */
    private Integer parentId;

    /**
     * 父类评论类型
     */
    private Integer type;

    private Integer commentor;

    private Long gmtCreate;

    private Long gmtModified;

    private Long likeCount;

    private String content;

    private User user;

    private String liker;

    private static final long serialVersionUID = 1L;
}

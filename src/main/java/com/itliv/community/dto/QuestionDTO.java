package com.itliv.community.dto;

import com.itliv.community.model.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class QuestionDTO implements Serializable {
    private Integer id;

    private String title;

    private String content;

    private Long gmtCreate;

    private Long gmtModified;

    private Integer creator;

    private Integer commentCount;

    private Integer viewCount;

    private Integer likeCount;

    private Integer collectCount;

    private String tag;

    private User user;

    private static final long serialVersionUID = 1L;
}

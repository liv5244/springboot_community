package com.itliv.community.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * question
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question implements Serializable {
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

    private static final long serialVersionUID = 1L;
}
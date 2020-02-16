package com.itliv.community.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private Integer id;
    private String title;
    private String content;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private Integer coolectCount;
    private String tag;

    private User user;

    public Question(Integer id, String title, String content, Long gmtCreate, Long gmtModified, Integer creator, Integer commentCount, Integer viewCount, Integer likeCount, Integer coolectCount, String tag) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.creator = creator;
        this.commentCount = commentCount;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.coolectCount = coolectCount;
        this.tag = tag;
    }
}

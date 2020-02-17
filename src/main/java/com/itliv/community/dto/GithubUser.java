package com.itliv.community.dto;

import lombok.Data;

@Data
public class GithubUser {
//    作为用户的唯一标志
    private Long id;
//    用户的用户名
    private String login;
    private String bio;
    //头像
    private String avatarUrl;
}

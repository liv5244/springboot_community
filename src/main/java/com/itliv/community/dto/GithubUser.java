package com.itliv.community.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GithubUser {
//    作为用户的唯一标志
    private Long id;
//    用户的用户名
    private String login;
    private String bio;
}

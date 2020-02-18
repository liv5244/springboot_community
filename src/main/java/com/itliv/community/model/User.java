package com.itliv.community.model;

import java.io.Serializable;
import lombok.Data;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {
    private Integer id;

    private String name;

    private String accountId;

    private String avatarUrl;

    private String token;

    private String gmtCreate;

    private String gmtModified;

    private static final long serialVersionUID = 1L;
}
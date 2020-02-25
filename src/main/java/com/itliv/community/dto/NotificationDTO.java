package com.itliv.community.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class NotificationDTO implements Serializable {
    private Integer id;

    private Integer notifier;

    private Integer receiver;

    private Integer outerid;

    private Integer type;

    private Long gmtCreate;

    private Integer status;

    private String name;

    private String quesName;

    private String quesGmtCreate;

    private String commentName;

    private String comGmtCreate;

    private static final long serialVersionUID = 1L;
}

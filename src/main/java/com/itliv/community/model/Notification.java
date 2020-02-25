package com.itliv.community.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * notification
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification implements Serializable {
    private Integer id;

    private Integer notifier;

    private Integer receiver;

    private Integer outerid;

    private Integer type;

    private Long gmtCreate;

    private Integer status;

    private static final long serialVersionUID = 1L;
}
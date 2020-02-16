package com.itliv.community.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一向前端返回数据的格式
 */
@Getter
@Setter
public class Msg {
    private String msg;
    //code=1 成功  code = 0 失败
    private int code;
    private Map<String, Object> ext = new HashMap<>();

    public static Msg success() {
        Msg result = new Msg();
        result.msg = "success";
        result.code = 1;
        return result;
    }

    public static Msg fail() {
        Msg result = new Msg();
        result.msg = "failed";
        result.code = 0;
        return result;
    }

    public Msg extend(String name, Object object) {
        this.ext.put(name, object);
        return this;
    }
}

package com.itliv.community.dto;

import lombok.Data;

@Data
public class ImgDto {
//    上传的后台只需要返回一个 JSON 数据，结构如下：
//    {
//        success : 0 | 1,           // 0 表示上传失败，1 表示上传成功
//                message : "提示的信息，上传成功或上传失败及错误信息等。",
//            url     : "图片地址"        // 上传成功时才返回
//    }
    private int success;
    private String message;
    private String url;
}

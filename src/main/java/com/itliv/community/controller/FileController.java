package com.itliv.community.controller;

import com.itliv.community.dto.ImgDto;
import com.itliv.community.provider.UCloudProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/upload")
public class FileController {
    @Autowired
    UCloudProvider uCloudProvider;

    @PostMapping("/img")
    @ResponseBody
    public ImgDto uploadImg(HttpServletRequest request) {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("editormd-image-file");
        String url;
        ImgDto imgDto = new ImgDto();
        try {
            url = uCloudProvider.upload(file.getInputStream(), file.getContentType(), file.getOriginalFilename());
            imgDto.setSuccess(1);
            imgDto.setUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgDto.setMessage("上传失败，请上传10M以内图片");
        return imgDto;
    }
}

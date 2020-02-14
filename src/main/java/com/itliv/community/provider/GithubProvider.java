package com.itliv.community.provider;

import com.alibaba.fastjson.JSON;
import com.itliv.community.dto.AccessTokenDTO;
import com.itliv.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    /**
     * 根据accessTokenDTO的值，向github提供的网址发送post请求，然后得到响应的内容，根据内容截取所需要的AccessToken
     * @param accessTokenDTO
     * @return
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String[] split = response.body().string().split("&");
            return split[0].split("=")[1];
        } catch (IOException e) {
        }
        return null;
    }

    /**
     * 根据accessToken得到用户的信息，然后封装成json数据，用户只得到  login(用户名),id(作为唯一标志),bio()
     * @param accessToken
     * @return
     */
    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            GithubUser githubUser = JSON.parseObject(response.body().string(), GithubUser.class);
            return githubUser;
        } catch (IOException e) {
        }
        return null;
    }
}

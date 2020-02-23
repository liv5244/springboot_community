package com.itliv.community.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tag {

    public static List<TagDTO> getTags() {
        List<TagDTO> tagDTOs = new ArrayList<>();
        List<String> tagName1  = Arrays.asList("javascript ","前端", "vue.js","css", "html", "html5", "node.js", "react.js", "jquery", "css3 ","es6", "typescript", "chromenpm", "bootstrap", "sassless", "chrome-devtools", "firefox","angular", "coffee", "script", "safari", "postcss", "postman", "fiddler", "webkit", "yarn ","firebug", "edge");

        List<String> tagName2  = Arrays.asList("php","java","node.js","python","c++","c","golang","spring","django","springboot","flask","c#","swoole","ruby","asp.net","ruby-on-rails","scala","rust","lavarel","爬虫","后端");

        List<String> tagName3= Arrays.asList("java","android","ios","objective-c","小程序","swift","react-native","xcode","android-studio","webapp","flutter","kotlin");

        List<String> tagName4= Arrays.asList("mysql","redis","mongodb","sql数据库","json","elasticsearch","nosql","memcached","postgresql","sqlite","mariadb");

        List<String> tagName5= Arrays.asList("linux","nginx","docker","apache","centos","ubuntu","服务器负载均衡","运维","ssh","vagrant","容器","jenkins","devops","debian","fabric");

        List<String> tagName6= Arrays.asList("算法","机器学习","人工智能","深度学习","数据挖掘","tensorflow","神经网络","图像识别","人脸识别","自然语言处理","机器人","pytorch","自动驾驶");

        List<String> tagName7= Arrays.asList("git","github","macos","visual-studio-code","windows","vimsublime-text","intellij-idea","eclipse","phpstorm","webstorm","编辑器","svn","visual-studio","pycharme","macs");

        List<String> tagName8= Arrays.asList("程序员","http","segmentfault","https","安全","websocket","restful","xss","区块链","csrf","graphql","rpc","比特币","以太坊","udp","智能合约","tcp");

        tagDTOs.add(new TagDTO("前端", tagName1));
        tagDTOs.add(new TagDTO("后端", tagName2));
        tagDTOs.add(new TagDTO("移动端", tagName3));
        tagDTOs.add(new TagDTO("数据库", tagName4));
        tagDTOs.add(new TagDTO("运维", tagName5));
        tagDTOs.add(new TagDTO("人工智能", tagName6));
        tagDTOs.add(new TagDTO("工具", tagName7));
        tagDTOs.add(new TagDTO("其他", tagName8));

        return tagDTOs;
   }
}

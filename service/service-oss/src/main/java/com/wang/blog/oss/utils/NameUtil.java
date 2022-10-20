package com.wang.blog.oss.utils;

import cn.hutool.core.date.DateUtil;

import java.util.UUID;

//  生成唯一上传文件名称
public class NameUtil {
    public static String getRandomImgName(String fileName) {

        int index = fileName.lastIndexOf(".");

        if ((fileName == null || fileName.isEmpty()) || index == -1){
            throw new IllegalArgumentException();
        }
        // 获取文件后缀
        String suffix = fileName.substring(index);
        // 生成UUID
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // 生成上传至云服务器的路径
        String path = "code/duck/" + DateUtil.today() + "-" + uuid + suffix;
        return path;
    }
}

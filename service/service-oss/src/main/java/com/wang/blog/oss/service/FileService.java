package com.wang.blog.oss.service;

import com.wang.blog.oss.utils.QiNiuConfig;
import org.springframework.web.multipart.MultipartFile;

public abstract class FileService {
    protected QiNiuConfig config;
    //  1、上傳文件並獲取上傳文件url
    public abstract String upload(MultipartFile file);
    //  2、文件删除并返回是否成功
    public abstract Boolean delete(String fileKey);
}

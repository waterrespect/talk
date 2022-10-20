package com.wang.blog.oss.service.Impl;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.wang.blog.oss.service.FileService;
import com.wang.blog.oss.utils.NameUtil;
import com.wang.blog.oss.utils.QiNiuConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FileServiceImpl extends FileService {
    // 1、七牛文件上传管理器
    private UploadManager uploadManager;
    private String token;

    // 2、七牛认证管理
    private Auth auth;

    public FileServiceImpl(QiNiuConfig config) {
        this.config = config;
        init();
    }

    private void init() {
        // 构造一个带指定Zone对象的配置类, 注意这里的Zone.zone0需要根据主机选择
        uploadManager = new UploadManager(new Configuration(Zone.huanan()));
        auth = Auth.create(config.getAccessKey(), config.getSecretKey());
        // 根据命名空间生成的上传token
        token = auth.uploadToken(config.getBucketName());
    }

    //  1、上傳文件並獲取上傳文件url
    @Override
    public String upload(MultipartFile file) {
        try{
            // 上传图片文件
            String fileName = file.getOriginalFilename();
            String imgName = NameUtil.getRandomImgName(fileName);
            FileInputStream inputStream = (FileInputStream) file.getInputStream();
            Response res = uploadManager.put(inputStream, imgName, token, null, null);

            if(!res.isOK()) {
                throw new RuntimeException("上传七牛出错：" + res.toString());
            }
            // 解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(res.bodyString(), DefaultPutRet.class);

            String path = config.getDomain() + "/" + putRet.key;

            return path;
        } catch(QiniuException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //  2、文件删除并返回是否成功
    @Override
    public Boolean delete(String fileKey) {
        Configuration cfg = new Configuration(Zone.huanan());
        Auth auth = Auth.create(config.getAccessKey(), config.getSecretKey());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try{
            if(null != fileKey) {
                Response delete = bucketManager.delete(config.getBucketName(), fileKey);
                boolean ok = delete.isOK();
                return ok;
            }
        }catch (QiniuException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}

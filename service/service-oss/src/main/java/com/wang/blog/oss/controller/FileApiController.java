package com.wang.blog.oss.controller;

import com.wang.blog.common.result.Result;
import com.wang.blog.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api("oss管理")
@RestController
@RequestMapping("/api/oss/file")
public class FileApiController {
    @Autowired
    private FileService fileService;

    //  1、上傳文件到七牛云
    @ApiOperation(value = "上傳文件到七牛云")
    @PostMapping("fileUpload")
    public Result fileUpload(MultipartFile file) {
        if(!file.isEmpty()) {
            String path = fileService.upload(file);
            System.out.println("上传成功" + path);
            return Result.ok(path);
        }
        return Result.fail("上传失败");
    }

    //  2、七牛云文件删除
    @ApiOperation(value = "七牛云文件删除")
    @GetMapping("fileDelete/{fileKey}")
    public Result fileDelete(@PathVariable String fileKey) {
        System.out.println("文件名字" + fileKey);
        if (!fileKey.isEmpty()) {
            fileKey = "code/duck/" + fileKey;
            Boolean delete = fileService.delete(fileKey);
            if (delete) {
                return Result.ok(delete);
            } else {
                return Result.fail("删除失败");
            }
        }
        return Result.fail("没有Key被删除");
    }
}

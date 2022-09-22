package com.wang.blog.common.exception;

import com.wang.blog.common.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class GlobalExceptionHandler {
    @ExceptionHandler(BlogException.class)
    @ResponseBody   // 可以让结果通过json形式输出
    public Result error(BlogException e) {
        e.printStackTrace();
        return Result.fail();
    }
}

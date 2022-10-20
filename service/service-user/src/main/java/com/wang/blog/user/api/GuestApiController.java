package com.wang.blog.user.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.blog.common.result.Result;
import com.wang.blog.model.user.Guest;
import com.wang.blog.user.service.GuestService;
import com.wang.blog.vo.user.GuestQueryVo;
import com.wang.blog.vo.user.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = "用户登录")
@RestController
@RequestMapping("/api/user")
public class GuestApiController {
    @Autowired
    private GuestService guestService;
    /**
     * 用户登录(手机号、微信)
     * @param loginVo
     * @return
     */
    @ApiOperation(value = "用户登录(手机号、微信),返回用户信息，toekn")
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo) {
        Map<String, Object> info = guestService.loginGuest(loginVo);
        return Result.ok(info);
    }

}

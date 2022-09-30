package com.wang.blog.user.api;

import com.wang.blog.common.result.Result;
import com.wang.blog.user.service.GuestService;
import com.wang.blog.vo.user.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@Api(tags = "用户登录")
@RestController
@RequestMapping("/api/guest")
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

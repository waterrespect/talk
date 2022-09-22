package com.wang.blog.user.api;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户登录")
@RestController
@RequestMapping("/api/guest")
public class GuestApiController {

}

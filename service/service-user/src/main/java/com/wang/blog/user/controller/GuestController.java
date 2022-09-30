package com.wang.blog.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.blog.common.result.Result;
import com.wang.blog.model.user.Guest;
import com.wang.blog.user.service.GuestService;
import com.wang.blog.vo.user.GuestQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("平臺用戶管理")
@RestController
@RequestMapping("/admin/user")
public class GuestController {
    @Autowired
    private GuestService guestService;

    @ApiOperation(value = "用戶列表,帶分頁查詢")
    @GetMapping("{page}/{limit}")
    public Result list(@PathVariable Long page,
                       @PathVariable Long limit,
                       GuestQueryVo guestQueryVo) {
        Page<Guest> pageParam = new Page<>(page, limit);
        IPage<Guest> pageModel
                = guestService.selectPage(pageParam, guestQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation(value = "用戶鎖定")
    @GetMapping("lock/{userId}/{status}")
    public Result lock(@PathVariable Long userId,
                       @PathVariable Integer status) {
        guestService.lock(userId, status);
        return Result.ok();
    }
}

package com.wang.blog.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.blog.model.user.Guest;
import com.wang.blog.vo.user.LoginVo;

import java.util.Map;

public interface GuestService extends IService<Guest> {
    //  TODO 前端用户管理
    //  1、手机号登录接口
    Map<String, Object> loginGuest(LoginVo loginVo);

    //  TODO 后台用户管理
    //  1、用户列表，带分页查询

}

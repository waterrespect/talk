package com.wang.blog.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.blog.model.user.Guest;
import com.wang.blog.vo.user.GuestQueryVo;
import com.wang.blog.vo.user.LoginVo;

import java.util.Map;

public interface GuestService extends IService<Guest> {
    //  TODO 前端用户管理
    //  1、手机号登录接口
    Map<String, Object> loginGuest(LoginVo loginVo);
    //  2、查找openid是否已經存在
    Guest selectWXInfoOpenId(String openid);
    //  TODO 后台用户管理
    //  1、用户列表，带分页查询
    IPage<Guest> selectPage(Page<Guest> pageParam, GuestQueryVo guestQueryVo);
    //  2、用户锁定
    void lock(Long user_id, Integer status);
    //  3、用户详情
    Map<String, Object> show(Long user_id);
    //  4、审批
    void approval(Long user_id, Integer authStatus);
}

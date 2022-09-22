package com.wang.blog.user.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.blog.common.exception.BlogException;
import com.wang.blog.common.result.ResultCodeEnum;
import com.wang.blog.model.user.Guest;
import com.wang.blog.user.mapper.GuestServiceMapper;
import com.wang.blog.user.service.GuestService;
import com.wang.blog.vo.user.LoginVo;
import org.springframework.util.StringUtils;

import java.util.Map;

public class GuestServiceImpl extends ServiceImpl<GuestServiceMapper, Guest> implements GuestService {
    //  TODO 前端用户管理
    //  1、手机号登录接口
    @Override
    public Map<String, Object> loginGuest(LoginVo loginVo) {
        //  1、从login_vo中获取手机号和验证码
        String phone = loginVo.getPhone();
        String code = loginVo.getCode();
        //  2、判断手机号和验证码是否为空
        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(code)) {
            throw new BlogException(ResultCodeEnum.PARAM_ERROR);
        }
        return null;
    }
}

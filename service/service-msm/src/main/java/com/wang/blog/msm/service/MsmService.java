package com.wang.blog.msm.service;

public interface MsmService {
    //  1、发送手机验证码
    boolean send(String phone, String code);
}

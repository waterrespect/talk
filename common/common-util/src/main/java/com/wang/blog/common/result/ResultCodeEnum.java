package com.wang.blog.common.result;

import lombok.Getter;

/**
 * 统一返回结果状态信息类
 */

@Getter
public enum ResultCodeEnum {
    //  1、服务器
    SUCCESS(200,"成功"),
    FAIL(201, "失败"),
    PARAM_ERROR( 202, "参数不正确"),
    SERVICE_ERROR(203, "服务异常"),
    DATA_ERROR(204, "数据异常"),
    DATA_UPDATE_ERROR(205, "数据版本异常"),

    LOGIN_AUTH(208, "未登陆"),
    PERMISSION(209, "没有权限"),

    CODE_ERROR(210, "验证码错误"),
    LOGIN_MOBLE_ERROR(211, "账号不正确"),
    LOGIN_DISABLED_ERROR(212, "该用户已被禁用"),
    REGISTER_MOBLE_ERROR(213, "手机号已被使用"),
    LOGIN_AURH(214, "需要登录"),
    LOGIN_ACL(215, "没有权限"),

    URL_ENCODE_ERROR( 216, "URL编码失败"),
    ILLEGAL_CALLBACK_REQUEST_ERROR( 217, "非法回调请求"),
    FETCH_ACCESSTOKEN_FAILD( 218, "获取accessToken失败"),
    FETCH_USERINFO_ERROR( 219, "获取用户信息失败"),
    LOGIN_ERROR( 23005, "登录失败"),

    PAY_RUN(220, "支付中"),
    CANCEL_ORDER_FAIL(225, "取消失败"),
    CANCEL_ORDER_NO(225, "不能取消"),

    //  标签
    LABEL_EXiST(300, "标签已存在"),
    LABEL_USED(301, "标签已经被文章使用"),

    //  文章
    UN_EXIST(235, "文章不存在")
    ;

    private Integer code;
    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}

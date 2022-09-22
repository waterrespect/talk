package com.wang.blog.model.user;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wang.blog.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
* @descroption:
* @author: Lw
* @date: 2022/9/17
 * @param: null
* @return null
**/

@Data
@ApiModel(description = "Guest")
@TableName("guest")
public class Guest extends BaseEntity{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "微信openid")
    @TableField("openid")
    private String openid;

    @ApiModelProperty(value = "用户昵称")
    @TableField("nick_name")
    private String nick_name;

    @ApiModelProperty(value = "用户手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "用户名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "认证状态（0：未认证 1：认证中 2：认证成功 -1：认证失败）")
    @TableField("auth_status")
    private Integer authStatus;

    @ApiModelProperty(value = "状态（0：锁定 1：正常）")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "用户IP")
    @TableField("ip")
    private String ip;

    @ApiModelProperty(value = "用户密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "用户邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "用户头像")
    @TableField("profile_photo")
    private String profile_photo;

    @ApiModelProperty(value = "用户生日")
    @TableField("birthday")
    private Date birthday;

    @ApiModelProperty(value = "用户年龄")
    @TableField("age")
    private Integer age;
}

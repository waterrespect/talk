package com.wang.blog.dto.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.wang.blog.dto.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserDTO extends BaseEntity{

    @ApiModelProperty(value = "微信openid")
    private String openid;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "用户手机号")
    private String phone;

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "认证状态（0：未认证 1：认证中 2：认证成功 -1：认证失败）")
    private Integer authStatus;

    @ApiModelProperty(value = "状态（0：锁定 1：正常）")
    private Integer status;

    @ApiModelProperty(value = "用户IP")
    private String ip;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "用户头像")
    private String profilePhoto;

    @ApiModelProperty(value = "用户生日")
    private Date birthday;

    @ApiModelProperty(value = "用户年龄")
    private Integer age;
}

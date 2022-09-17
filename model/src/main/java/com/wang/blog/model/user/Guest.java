package com.wang.blog.model.user;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wang.blog.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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

    @ApiModelProperty(value = "用户IP")
    @TableField("user_ip")
    private String user_ip;

    @ApiModelProperty(value = "用户名")
    @TableField("user_name")
    private String user_name;

    @ApiModelProperty(value = "用户密码")
    @TableField("user_password")
    private String user_password;

    @ApiModelProperty(value = "用户邮箱")
    @TableField("user_email")
    private String user_email;

    @ApiModelProperty(value = "用户头像")
    @TableField("user_profile_photo")
    private String user_profile_photo;

    @ApiModelProperty(value = "用户生日")
    @TableField("user_age")
    private String user_age;

    @ApiModelProperty(value = "用户手机号")
    @TableField("user_telephone_number")
    private String user_telephone_number;

    @ApiModelProperty(value = "用户昵称")
    @TableField("user_nickname")
    private String user_nickname;
}

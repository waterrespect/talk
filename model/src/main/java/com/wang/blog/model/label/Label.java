package com.wang.blog.model.label;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wang.blog.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Label")
@TableName("labels")
public class Label extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标签名称")
    @TableField("label_name")
    private String labelName;

    @ApiModelProperty(value = "标签别名")
    @TableField("label_alias")
    private String labelAlias;

    @ApiModelProperty(value = "标签描述")
    @TableField("label_description")
    private String labelDescription;

}

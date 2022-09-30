package com.wang.blog.vo.label;


import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "标签查询")
public class LabelVo {

    @ApiModelProperty(value = "标签id")
    @TableField("id")
    private Long id;

    @ApiModelProperty(value = "标签名称")
    @TableField("label_name")
    private String label_name;

    @ApiModelProperty(value = "标签别名")
    @TableField("label_alias")
    private String label_alias;

    @ApiModelProperty(value = "标签描述")
    @TableField("label_description")
    private String label_description;

    @ApiModelProperty(value = "创建时间")
    private String createTimeBegin;

    @ApiModelProperty(value = "创建时间")
    private String createTimeEnd;
}

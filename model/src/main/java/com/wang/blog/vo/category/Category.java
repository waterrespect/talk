package com.wang.blog.vo.category;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "分类查询")
public class Category {

    @ApiModelProperty(value = "分类id")
    @TableField("id")
    private Long id;

    @ApiModelProperty(value = "分类名称")
    @TableField("sort_name")
    private String sortName;

    @ApiModelProperty(value = "分类别名")
    @TableField("sort_alias")
    private String sortAlias;

    @ApiModelProperty(value = "分类描述")
    @TableField("sort_description")
    private String sortDescription;

    @ApiModelProperty(value = "父分类ID")
    @TableField("parent_sort_id")
    private String parentSortId;

    @ApiModelProperty(value = "创建时间")
    private String createTimeBegin;

    @ApiModelProperty(value = "创建时间")
    private String createTimeEnd;

}

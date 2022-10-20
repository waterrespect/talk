package com.wang.blog.model.category;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wang.blog.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "sort")
@TableName("sorts")
public class Category extends BaseEntity {

    private static final long serialVersionUID = 1L;

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

    @ApiModelProperty(value = "是否包含子节点")
    @TableField(exist = false)
    private boolean hasChildren;
}

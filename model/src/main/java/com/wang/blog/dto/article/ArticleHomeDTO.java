package com.wang.blog.dto.article;

import com.wang.blog.dto.base.BaseEntity;
import com.wang.blog.dto.user.UserDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 *  文章返回
 */
@Data
@ApiModel(description = "article")
public class ArticleHomeDTO{

    @ApiModelProperty(value = "文章id")
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "更新时间")
    private String updateTime;

    @ApiModelProperty(value = "逻辑删除")
    private Integer isDelete;

    @ApiModelProperty(value = "文章标题")
    private String articleTitle;

    @ApiModelProperty(value = "文章浏览量")
    private Long articleViews;

    @ApiModelProperty(value = "文章评论总数")
    private Long articleCommentCount;

    @ApiModelProperty(value = "文章点赞数")
    private Long articleLikeCount;

    @ApiModelProperty(value = "缩略图")
    private String articleCover;

    @ApiModelProperty(value = "文章类型")
    private Long type;

    @ApiModelProperty(value = "原文链接")
    private String originalUrl;

    @ApiModelProperty(value = "状态值")
    private Long status;

    @ApiModelProperty(value = "文章分类")
    private String categoryName;

    @ApiModelProperty(value = "用户信息")
    private List<UserDTO> userDTOList;
}

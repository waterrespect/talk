package com.wang.blog.model.article;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wang.blog.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "article")
@TableName("article")
public class Article extends BaseEntity {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "发表用户ID")
    @TableField("user_id")
    private Long UserId;

    @ApiModelProperty(value = "文章标题")
    @TableField("article_title")
    private String articleTitle;

    @ApiModelProperty(value = "文章内容")
    @TableField("article_content")
    private String articleContent;

    @ApiModelProperty(value = "文章内容_html")
    @TableField("article_content_html")
    private String articleContentHtml;
    //  可以使用redis
    @ApiModelProperty(value = "文章浏览量")
    @TableField("article_views")
    private Long articleViews;

    @ApiModelProperty(value = "文章评论总数")
    @TableField("article_comment_count")
    private Long articleCommentCount;

    @ApiModelProperty(value = "文章点赞数")
    @TableField("article_like_count")
    private Long articleLikeCount;
    //
    @ApiModelProperty(value = "缩略图")
    @TableField("article_cover")
    private String articleCover;

    @ApiModelProperty(value = "文章分类")
    @TableField("category_id")
    private Long categoryId;

    @ApiModelProperty(value = "文章类型")
    @TableField("type")
    private Long type;

    @ApiModelProperty(value = "原文链接")
    @TableField("original_url")
    private String originalUrl;

    @ApiModelProperty(value = "状态值")
    @TableField("status")
    private Long status;
}

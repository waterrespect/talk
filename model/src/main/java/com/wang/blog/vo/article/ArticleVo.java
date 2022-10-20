package com.wang.blog.vo.article;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wang.blog.vo.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel(description = "article")
public class ArticleVo extends BaseEntity {

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

    @ApiModelProperty(value = "创建时间")
    private String createTimeBegin;

    @ApiModelProperty(value = "创建时间")
    private String createTimeEnd;
}

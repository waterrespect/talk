package com.wang.blog.dto.article;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 作者相关文章
 */
@Data
public class ArticleRecommendDTO {
    @ApiModelProperty(value = "文章id")
    private Long id;

    @ApiModelProperty(value = "缩略图")
    private String articleCover;

    @ApiModelProperty(value = "标题")
    private String articleTitle;

    @ApiModelProperty(value = "创建时间")
    private String createTime;
}

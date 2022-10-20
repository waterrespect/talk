package com.wang.blog.dto.article;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *  文章上下篇
 */
@Data
public class ArticlePaginationDTO {

    @ApiModelProperty(value = "文章id")
    private Long id;

    @ApiModelProperty(value = "文章缩略图")
    private String articleCover;

    @ApiModelProperty(value = "标题")
    private String articleTitle;

}

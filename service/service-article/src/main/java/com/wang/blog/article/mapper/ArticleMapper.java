package com.wang.blog.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.blog.dto.article.ArticleDTO;
import com.wang.blog.dto.article.ArticleHomeDTO;
import com.wang.blog.model.article.Article;
import com.wang.blog.vo.article.ArticleVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper extends BaseMapper<Article> {
    /**
     * 文章返回
     * @param current   页码
     * @param size      数目
     * @param articleVo 条件
     * @return  文章列表
     */
    List<ArticleHomeDTO> listArticleBacks(@Param("current") Long current, @Param("size") Long size, @Param("articleVo") ArticleVo articleVo);

    /**
     * 详细文章返回
     * @param id
     * @return
     */
    ArticleDTO articleById(@Param("id") Long id);


}

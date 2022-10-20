package com.wang.blog.article.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.blog.dto.Page.PageResult;
import com.wang.blog.dto.article.ArticleDTO;
import com.wang.blog.dto.article.ArticleHomeDTO;
import com.wang.blog.model.article.Article;
import com.wang.blog.vo.article.ArticleVo;

public interface ArticleService extends IService<Article> {
    //TODO 后端
    //  1、文章查找
    PageResult<ArticleHomeDTO> selectPage(Page<Article> pageParam, ArticleVo articleVo);
    //  2、文章增加/修改
    Long saveOrUpdateArticle(ArticleVo articleVo);
    //  3、文章id查找,返回详细文章
    ArticleDTO selectArticle(Long article_id);
    //  4、list
    ArticleDTO findlist(Long id);
}

package com.wang.blog.article.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.blog.article.mapper.ArticleMapper;
import com.wang.blog.article.service.ArticleService;
import com.wang.blog.common.exception.BlogException;
import com.wang.blog.common.result.ResultCodeEnum;
import com.wang.blog.config.utils.BeanCopyUtils;
import com.wang.blog.dto.Page.PageResult;
import com.wang.blog.dto.article.ArticleDTO;
import com.wang.blog.dto.article.ArticleHomeDTO;
import com.wang.blog.dto.article.ArticlePaginationDTO;
import com.wang.blog.dto.article.ArticleRecommendDTO;
import com.wang.blog.model.article.Article;
import com.wang.blog.vo.article.ArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

import static com.wang.blog.enums.ArticleStatusEnum.DELETED_FALSE;
import static com.wang.blog.enums.ArticleStatusEnum.PUBLIC_STATUS;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    //TODO 后端
    //  1、文章首页返回查找
    @Override
    public PageResult<ArticleHomeDTO> selectPage(Page<Article> pageParam, ArticleVo articleVo) {
        //1、查询文章
        List<ArticleHomeDTO> articleHomeDTOS = articleMapper.listArticleBacks(pageParam.getCurrent(), pageParam.getSize(), articleVo);
        Page<Article> articlePage = CountArticle(pageParam, articleVo);
        return new PageResult<>(articleHomeDTOS, articlePage.getTotal(), articlePage.getPages());
    }

    //  获得总文章数
    private Page<Article> CountArticle(Page<Article> pageParam, ArticleVo articleVo) {
        String title = articleVo.getArticleTitle();
        String content = articleVo.getArticleContent();
        Integer is_deleted = articleVo.getIsDeleted();
        Long category_id = articleVo.getCategoryId();
        Long type = articleVo.getType();
        Long status = articleVo.getStatus();

        String createTimeBegin = articleVo.getCreateTimeBegin();
        String createTimeEnd = articleVo.getCreateTimeEnd();

        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(title)) wrapper.like("title", title);
        if(!StringUtils.isEmpty(content)) wrapper.like("content", content);
        if(!StringUtils.isEmpty(is_deleted)) wrapper.eq("is_deleted", is_deleted);
        if(!StringUtils.isEmpty(category_id)) wrapper.eq("category_id", category_id);
        if(!StringUtils.isEmpty(type)) wrapper.eq("type", type);
        if(!StringUtils.isEmpty(status)) wrapper.eq("status", status);
        if(!StringUtils.isEmpty(createTimeBegin)) wrapper.ge("create_time", createTimeBegin);
        if(!StringUtils.isEmpty(createTimeEnd)) wrapper.le("create_time", createTimeEnd);
        Page<Article> articlePage = baseMapper.selectPage(pageParam, wrapper);
        return articlePage;
    }

    //  2、文章增加/修改
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long saveOrUpdateArticle(ArticleVo articleVo) {
        //  保存或修改文章
        Article article = BeanCopyUtils.copyObject(articleVo, Article.class);
        this.saveOrUpdate(article);
        return article.getId();
    }
    //  3、文章id查找,返回详细文章
    @Override
    public ArticleDTO selectArticle(Long article_id) {
        //1、文章本体
        ArticleDTO articleDTO = articleMapper.articleById(article_id);
        if(Objects.isNull(articleDTO)) {
            throw new BlogException(ResultCodeEnum.UN_EXIST);
        }
        //2、作者相关文章列表
        List<Article> articleList = baseMapper.selectList(new LambdaQueryWrapper<Article>()
                .select(Article::getId, Article::getArticleTitle, Article::getArticleCover, Article::getCreateTime)
                .eq(Article::getIsDeleted, DELETED_FALSE.getStatus())
                .eq(Article::getStatus, PUBLIC_STATUS.getStatus())
                .orderByDesc(Article::getId)
                .last("limit 5"));

        articleDTO.setAuthorArticleList(BeanCopyUtils.copyList(articleList, ArticleRecommendDTO.class));
        //3、标签

        //4、上一篇下一篇文章
        Article lastArticle = baseMapper.selectOne(new LambdaQueryWrapper<Article>()
                .select(Article::getId, Article::getArticleTitle, Article::getArticleCover)
                .eq(Article::getIsDeleted, DELETED_FALSE.getStatus())
                .eq(Article::getStatus, PUBLIC_STATUS.getStatus())
                .lt(Article::getId, article_id)
                .orderByDesc(Article::getId)
                .last("limit 1")
        );
        Article nextArticle = baseMapper.selectOne(new LambdaQueryWrapper<Article>()
                .select(Article::getId, Article::getArticleTitle, Article::getArticleCover)
                .eq(Article::getIsDeleted, DELETED_FALSE.getStatus())
                .eq(Article::getStatus, PUBLIC_STATUS.getStatus())
                .gt(Article::getId, article_id)
                .orderByDesc(Article::getId)
                .last("limit 1")
        );
        articleDTO.setLastArticle(BeanCopyUtils.copyObject(lastArticle, ArticlePaginationDTO.class));
        articleDTO.setNextArticle(BeanCopyUtils.copyObject(nextArticle, ArticlePaginationDTO.class));

        return articleDTO;
    }

    @Override
    public ArticleDTO findlist(Long id) {
        return null;
    }


}

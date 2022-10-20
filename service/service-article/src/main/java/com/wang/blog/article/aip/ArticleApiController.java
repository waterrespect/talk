package com.wang.blog.article.aip;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.blog.article.service.ArticleService;
import com.wang.blog.common.result.Result;
import com.wang.blog.common.utils.AuthContextHolder;
import com.wang.blog.dto.Page.PageResult;
import com.wang.blog.dto.article.ArticleDTO;
import com.wang.blog.dto.article.ArticleHomeDTO;
import com.wang.blog.model.article.Article;
import com.wang.blog.vo.article.ArticleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "前端文章管理")
@RestController
@RequestMapping("/api/article")
public class ArticleApiController {
    @Autowired
    private ArticleService articleService;

    //1、文章条件查找(缩略图战术) => 首页/多个/时间顺序/
    @ApiOperation(value = "查询文章返回list&count")
    @GetMapping("/show/{page}/{limit}")
    public Result getArticleHomeList(@PathVariable Long page,
                                     @PathVariable Long limit,
                                     ArticleVo articleVo) {
        Page<Article> param = new Page<>(page, limit);
        articleVo.setIsDeleted(0);
        PageResult<ArticleHomeDTO> result = articleService.selectPage(param, articleVo);
        return Result.ok(result);
    }
    //2、文章条件查找(详细展示) => 所有内容/评论/作者相关文章
    @ApiOperation(value = "selectArticle")
    @GetMapping("/find/{id}")
    public Result list(@PathVariable Long id) {
        return Result.ok(articleService.selectArticle(id));
    }
    //2、
    @ApiOperation(value = "保存文章、创建文章")
//    @PostMapping("auth/save")
    @PostMapping("save")
//    public Result savepatient(@RequestBody ArticleVo articleVo, HttpServletRequest request) {
    public Result savepatient(@RequestBody ArticleVo articleVo) {
        //  獲取當前就診人id
//        Long userId = AuthContextHolder.getUserId(request);
        articleVo.setUserId((long) 1);
        Long article_id = articleService.saveOrUpdateArticle(articleVo);
        return Result.ok(article_id);
    }
}

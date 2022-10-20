package com.wang.blog.label.controller;

import com.wang.blog.common.result.Result;
import com.wang.blog.label.service.CategoryService;
import com.wang.blog.model.category.Category;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("平臺分类管理")
@RestController
@RequestMapping("/admin/label/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //  1、根据数据id查询子数据列表
    @ApiOperation(value = "根据数据id查询子数据列表")
    @GetMapping("findChildren/{id}")
    public Result findChildData(@PathVariable Long id) {
        List<Category> list = categoryService.findChildData(id);
        return Result.ok(list);
    }
}

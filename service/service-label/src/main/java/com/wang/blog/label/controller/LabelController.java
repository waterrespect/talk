package com.wang.blog.label.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.blog.common.result.Result;
import com.wang.blog.label.service.LabelService;
import com.wang.blog.model.label.Label;
import com.wang.blog.vo.label.LabelVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("平臺标签管理")
@RestController
@RequestMapping("/admin/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @ApiOperation(value = "标签列表,帶分頁查詢")
    @GetMapping("{page}/{limit}")
    public Result list(@PathVariable Long page,
                       @PathVariable Long limit,
                       LabelVo labelVo) {
        Page<Label> pageParam = new Page<>(page, limit);
        IPage<Label> pageModel
                = labelService.selectPage(pageParam, labelVo);

        return Result.ok(pageModel);
    }

    @ApiOperation(value = "添加或修改标签")
    @PostMapping("/addOrUpdate")
    public Result saveOrUpdateTag(LabelVo labelVo) {
        labelService.saveOrUpdateLabel(labelVo);
        return Result.ok();
    }
}

package com.wang.blog.label.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.blog.model.label.Label;
import com.wang.blog.vo.label.LabelVo;
import java.util.List;

public interface LabelService extends IService<Label> {
    //TODO 前端

    //TODO 后台
    //1、查询所有
    IPage<Label> selectPage(Page<Label> pageParam, LabelVo labelVo);
    //2、添加修改标签
    void saveOrUpdateLabel(LabelVo labelVo);
    //3、删除标签
    Boolean deleteLabel(List<Integer> labelIdList);
}

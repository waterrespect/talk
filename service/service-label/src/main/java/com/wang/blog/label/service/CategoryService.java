package com.wang.blog.label.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.blog.model.category.Category;
import java.util.List;

public interface CategoryService extends IService<Category> {

    //  1、根据数据id查询子数据列表
    List<Category> findChildData(Long parent_id);
    //  2、插入分类

    //  3、文章选择分类

}

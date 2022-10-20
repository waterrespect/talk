package com.wang.blog.label.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.blog.label.mapper.CategoryMapper;
import com.wang.blog.label.service.CategoryService;
import com.wang.blog.model.category.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    //  1、根据数据id查询子数据列表
    @Override
    public List<Category> findChildData(Long parent_id) {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_sort_id", parent_id);
        //  搜索该父节点(parent_id)的子节点分类
        List<Category> categoryList = baseMapper.selectList(wrapper);
        for (Category category : categoryList) {
            boolean isChild = isChildren(category.getId());
            category.setHasChildren(isChild);
        }
        return categoryList;
    }
    //  判断子节点id下面是否有子节点
    private boolean isChildren(Long id) {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_sort_id", id);
        Integer count = baseMapper.selectCount(wrapper);
        return count > 0;
    }
}

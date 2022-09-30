package com.wang.blog.label.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.blog.common.exception.BlogException;
import com.wang.blog.common.result.ResultCodeEnum;
import com.wang.blog.config.utils.BeanCopyUtils;
import com.wang.blog.label.mapper.LabelMapper;
import com.wang.blog.label.service.LabelService;
import com.wang.blog.model.label.Label;
import com.wang.blog.vo.label.LabelVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements LabelService {


    //TODO 后台
    //1、查询所有
    @Override
    public IPage<Label> selectPage(Page<Label> pageParam, LabelVo labelVo) {
        //  1、labelQueryVo 獲取條件值
        String name = labelVo.getLabel_name();//   名称
        String createTimeBegin = labelVo.getCreateTimeBegin();//    開始時間
        String createTimeEnd = labelVo.getCreateTimeEnd();//    結束時間

        //  2、非空判断
        QueryWrapper<Label> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(name)) wrapper.like("label_name", name);
        if(!StringUtils.isEmpty(createTimeBegin)) wrapper.ge("createTime", createTimeBegin);
        if(!StringUtils.isEmpty(createTimeEnd)) wrapper.le("createTime", createTimeEnd);
        //  3、調用方法
        Page<Label> labelPage = baseMapper.selectPage(pageParam, wrapper);

        return labelPage;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateLabel(LabelVo labelVo) {
        //  1、查询
        Label existLabel = baseMapper.selectOne(new LambdaQueryWrapper<Label>()
                .select(Label::getId)
                .eq(Label::getLabel_name, labelVo.getLabel_name()));//通过名字查询
        System.out.println("label => " + existLabel);
        if (Objects.nonNull(existLabel) && !existLabel.getId().equals(labelVo.getId())) {// 存在、id相同
            throw new BlogException(ResultCodeEnum.LABEL_EXiST); // 标签已存在
        }
        Label label = BeanCopyUtils.copyObject(labelVo, Label.class);
        this.saveOrUpdate(label);// 保存或修改
    }

    @Override
    public Boolean deleteLabel(List<Integer> labelIdList) {
        //  查询标签下是否有文章
//        baseMapper.selectCount(new LambdaQueryWrapper<>())
        return null;
    }
    //2、添加标签

}

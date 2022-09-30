package com.wang.blog.user.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.blog.common.exception.BlogException;
import com.wang.blog.common.helper.JwtHelper;
import com.wang.blog.common.result.ResultCodeEnum;
import com.wang.blog.enums.AuthStatusEnum;
import com.wang.blog.model.user.Guest;
import com.wang.blog.user.mapper.GuestMapper;
import com.wang.blog.user.service.GuestService;
import com.wang.blog.vo.user.GuestQueryVo;
import com.wang.blog.vo.user.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class GuestServiceImpl extends ServiceImpl<GuestMapper, Guest> implements GuestService {
    //  TODO 前端用户管理
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 1、手机号登录接口
     * @param loginVo
     * @return
     */
    @Override
    public Map<String, Object> loginGuest(LoginVo loginVo) {
        //  1、从login_vo中获取手机号和验证码
        String phone = loginVo.getPhone();
        String code = loginVo.getCode();
        //  2、判断手机号和验证码是否为空
        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(code)) {
            throw new BlogException(ResultCodeEnum.PARAM_ERROR);
        }
        //  3、判断验证码是否一致
        String redisCode = redisTemplate.opsForValue().get(phone);
        if(!code.equals(redisCode)) {
            throw new BlogException(ResultCodeEnum.CODE_ERROR);
        }
        //  4、判断手机号是否第一次登录,查询数据库
        Guest guest = null;
        if(!StringUtils.isEmpty(loginVo.getOpenid())) {
            guest = this.selectWXInfoOpenId(loginVo.getOpenid());
            if(null != guest) {
                guest.setPhone(loginVo.getPhone());
                this.updateById(guest);
            } else {
                throw new BlogException(ResultCodeEnum.DATA_ERROR);
            }
        }
        //  如果微信openid爲空，第一次登錄
        if(null == guest) {
            //  第一次登录，添加信息
            QueryWrapper<Guest> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("phone", phone);
            guest = baseMapper.selectOne(queryWrapper);
            //  如果没找到
            if(null == guest) {
                guest = new Guest();
                guest.setName("");
                guest.setPhone(phone);
                guest.setStatus(1); // 状态还未验证
                baseMapper.insert(guest);
            }
        }
        //  校验是否被禁用
        if(guest.getStatus() == 0) {
            throw new BlogException(ResultCodeEnum.LOGIN_DISABLED_ERROR);
        }

        //  如果不是第一次，直接登录;返回登录信息，用户名,token信息
        Map<String, Object> map = new HashMap<>();
        String name = guest.getName();
        if(StringUtils.isEmpty(name)) {
            name = guest.getNick_name();
        }
        if(StringUtils.isEmpty(name)) {
            name = guest.getPhone();
        }
        map.put("name", name);
        //  Token字符串
        String token = JwtHelper.createToken(guest.getId(), name);
        map.put("token", token);
        return map;
    }

    /**
     * 2、查找openid是否已經存在
     * @param openid
     * @return
     */
    @Override
    public Guest selectWXInfoOpenId(String openid) {
        QueryWrapper<Guest> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid", openid);
        Guest guest = baseMapper.selectOne(queryWrapper);
        return guest;
    }


    // TODO 后端管理
    //  1、用户列表，带分页查询
    @Override
    public IPage<Guest> selectPage(Page<Guest> pageParam, GuestQueryVo guestQueryVo) {
        //  1、guestQueryVo 獲取條件值
        String name = guestQueryVo.getKeyword();//   用戶名稱
        Integer status = guestQueryVo.getStatus();// 用戶狀態
        Integer authStatus = guestQueryVo.getAuthStatus();// 認證狀態
        String createTimeBegin = guestQueryVo.getCreateTimeBegin();//    開始時間
        String createTimeEnd = guestQueryVo.getCreateTimeEnd();//    結束時間
        //  2、非空判断
        QueryWrapper<Guest> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(name)) wrapper.like("name", name);
        if(!StringUtils.isEmpty(status)) wrapper.eq("status", status);
        if(!StringUtils.isEmpty(authStatus)) wrapper.eq("auth_Status", authStatus);
        if(!StringUtils.isEmpty(createTimeBegin)) wrapper.ge("createTimeBegin", createTimeBegin);
        if(!StringUtils.isEmpty(createTimeEnd)) wrapper.le("createTimeEnd", createTimeEnd);
        //  3、調用方法
        Page<Guest> userInfoPage = baseMapper.selectPage(pageParam, wrapper);
        //  封裝状态值
        userInfoPage.getRecords().stream().forEach(item -> {
            this.packageGuest(item);
        });
        return userInfoPage;
    }
    //  2、用户锁定
    @Override
    public void lock(Long user_id, Integer status) {
        //  1、用戶查詢
        //  2、判斷
        //  3、修改
        if(status.intValue() == 0 || status.intValue() == 1) {
            Guest guest = baseMapper.selectById(user_id);
            guest.setStatus(status);
            baseMapper.updateById(guest);
        }
    }
    //  3、用户详情
    @Override
    public Map<String, Object> show(Long user_id) {
        Map<String, Object> map = new HashMap<>();
        //  1、查詢用戶信息
        //  封裝
        Guest guest = this.packageGuest(baseMapper.selectById(user_id));
        map.put("guest", guest);

        return null;
    }
    //  4、审批
    @Override
    public void approval(Long user_id, Integer authStatus) {

    }

    /**
     * 返回用户状态
     * @param guest
     * @return
     */
    private Guest packageGuest(Guest guest) {
        //  認證狀態，賬戶狀態
        guest.getParam().put("authStatusString", AuthStatusEnum.getStatusNameByStatus(guest.getAuthStatus()));

        String statusString = guest.getStatus().intValue() == 0 ? "鎖定" : "正常";
        guest.getParam().put("statusString", statusString);
        return guest;
    }
}

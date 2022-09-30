package com.wang.blog.config.utils;

import java.util.ArrayList;
import java.util.List;

/**
* @descroption:复制对象或集合属性
* @author: Lw
* @date: 2022/9/26
 * @param :null
* @return null
**/

public class BeanCopyUtils {

    /**
     * 复制对象
     * @param source 源
     * @param target 目标
     * @param <T>
     * @return
     */
    public static <T> T copyObject(Object source, Class<T> target) {
        T temp = null;
        try {
            temp = target.newInstance();
            if (null != source) {
                org.springframework.beans.BeanUtils.copyProperties(source, temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * 拷贝集合
     * @param source 源
     * @param target 目标
     * @param <T>   集合
     * @param <S>
     * @return
     */
    public static <T, S> List<T> copyList(List<S> source, Class<T> target) {
        List<T> list = new ArrayList<>();
        if (null != source && source.size() > 0) {
            for (Object obj : source) {
                list.add(BeanCopyUtils.copyObject(obj, target));
            }
        }
        return list;
    }

}

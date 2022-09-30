package com.wang.blog.label.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

@Component
@MapperScan("com.wang.blog.label.mapper")
public class LabelConfig {
}

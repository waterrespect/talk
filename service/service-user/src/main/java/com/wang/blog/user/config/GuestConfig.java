package com.wang.blog.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.wang.blog.user.mapper")
public class GuestConfig {
}

package com.wang.blog.oss.utils;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class QiNiuConfig{

    @Value("${qiniu.oss.domain}")
    private String domain;
    @Value("${qiniu.oss.accessKey}")
    private String accessKey;
    @Value("${qiniu.oss.secretKey}")
    private String secretKey;
    @Value("${qiniu.oss.buckertName}")
    private String bucketName;

}

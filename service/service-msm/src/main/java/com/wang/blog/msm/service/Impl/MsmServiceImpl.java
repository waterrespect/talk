package com.wang.blog.msm.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.wang.blog.msm.service.MsmService;
import com.wang.blog.msm.utils.ConstantPropertiesUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class MsmServiceImpl implements MsmService {

    //  1、发送手机验证码
    @Override
    public boolean send(String phone, String code) {
        //  1、判断手机号是否为空
        if(StringUtils.hasLength(phone)) {
            return false;
        }
        //  2、整合阿里云短信服务
        IAcsClient client = the_client();
        CommonRequest request = the_request(phone);
        //验证码  使用json格式   {"code":"123456"}
        Map<String,Object> param = new HashMap();
        param.put("code",code);
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));

        //调用方法进行短信发送
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }



    //  短信参数封装
    private IAcsClient the_client() {
        //  相关参数
        DefaultProfile profile = DefaultProfile.
                getProfile(ConstantPropertiesUtils.REGION_Id,
                        ConstantPropertiesUtils.ACCESS_KEY_ID,
                        ConstantPropertiesUtils.SECRECT);
        // Alibaba Cloud SDK for Java 阿里云账号和访问密钥（AccessKey）访问阿里巴巴
        IAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
    private CommonRequest the_request(String phone) {
        // Alibaba CommonRequest调用方式可实现任意OpenAPI接口的调用
        CommonRequest request = new CommonRequest();

        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);         //  request 参数设置
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        //手机号
        request.putQueryParameter("PhoneNumbers", phone);
        //签名名称
        request.putQueryParameter("SignName", "阿里云短信测试");
        //模板code
        request.putQueryParameter("TemplateCode", "SMS_154950909");

        return request;
    }
}

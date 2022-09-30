package com.wang.blog.user.api;

import com.alibaba.fastjson.JSONObject;
import com.wang.blog.common.helper.JwtHelper;
import com.wang.blog.common.result.Result;
import com.wang.blog.model.user.Guest;
import com.wang.blog.user.service.GuestService;
import com.wang.blog.user.utils.ConstantWxPropertiesUtils;
import com.wang.blog.user.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/ucenter/wx")
public class WeiXinApiController {
    @Autowired
    private GuestService guestService;

    /**
     * 1、前端js获得参数展现二维码
     * @return
     */
    @GetMapping("getLoginParam")
    @ResponseBody   //  返回數據
    public Result getQrConnect() {
        try {
            Map<String, Object> map = new HashMap<>();
            String wxOpenRedirectUrl = ConstantWxPropertiesUtils.WX_OPEN_REDIRECT_URL;
            wxOpenRedirectUrl = URLEncoder.encode(wxOpenRedirectUrl, "utf-8");

            map.put("appid", ConstantWxPropertiesUtils.WX_OPEN_APP_ID);
            map.put("scope", "snsapi_login");
            map.put("redirect_uri", wxOpenRedirectUrl);
            map.put("state", System.currentTimeMillis()+"");

            return Result.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 2、微信掃描后回調的方法
     * @param code
     * @param state
     * @return
     */
    @GetMapping("callback")
    public String callbakc(String code, String state) {
        //TODO  1、獲得臨時票據code
        System.out.println("code:" + code);
        System.out.println("state:" + state);
        //TODO  2、使用code和appid以及appscrect换取access_token
        StringBuffer baseAccessTokenUrl = new StringBuffer()
                .append("https://api.weixin.qq.com/sns/oauth2/access_token")
                .append("?appid=%s")
                .append("&secret=%s")
                .append("&code=%s")
                .append("&grant_type=authorization_code");

        String accessTokenUrl = String.format(baseAccessTokenUrl.toString(),
                ConstantWxPropertiesUtils.WX_OPEN_APP_ID,
                ConstantWxPropertiesUtils.WX_OPEN_APP_SECRET,
                code);

        //TODO 3、使用httoclient請求accsstokenurl地址
        try{
            //  获取微信accesstokenurl地址
            String accessTokenInfo = HttpClientUtils.get(accessTokenUrl);
            //  s => 返回的信息 獲取accsstoken 、openid
            System.out.println("accessTokenInfo" +accessTokenInfo);
            JSONObject jsonObject = JSONObject.parseObject(accessTokenInfo);
            String access_token = jsonObject.getString("access_token");
            String openid = jsonObject.getString("openid");
            //TODO  先判斷數據庫中是否已經存在該微信信息 => 通過openid
            Guest guest =  guestService.selectWXInfoOpenId(openid);
            //  如果沒有該微信信息,直接插入數據庫
            if(null == guest) {
                //TODO  3、openid 和acccess_token請求微信地址,獲得掃碼人信息
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                String guestUrl = String.format(baseUserInfoUrl, access_token, openid);
                String resultGuestInfo = HttpClientUtils.get(guestUrl);
                System.out.println("resultGuestInfo:" + resultGuestInfo);
                //  解析用戶信息
                JSONObject resultGuestInfoJson = JSONObject.parseObject(resultGuestInfo);
                String nickname = resultGuestInfoJson.getString("nickname");//   昵稱
                String headimgurl = resultGuestInfoJson.getString("headimgurl");//   頭像
                //TODO 4、將獲取的掃碼人信息傳入數據庫中
                guest = new Guest();
                guest.setNick_name(nickname);
                guest.setOpenid(openid);
                guest.setStatus(1);
                guestService.save(guest);
            }
            //TODO 5、返回token和name信息
            Map<String, Object> map = new HashMap<>();
            //  name
            String name = guest.getName();
            if(StringUtils.isEmpty(name)) {
                name = guest.getNick_name();
            }
            if(StringUtils.isEmpty(name)) {
                name = guest.getPhone();
            }
            map.put("name", name);
            //  判斷是否有手機號，如果沒有返回openid
            //  前端判斷:openid不爲空，綁定手機號,爲空，不綁定
            if(StringUtils.isEmpty(guest.getPhone())) {
                map.put("openid", guest.getOpenid());
            } else {
                map.put("openid", "");
            }
            //  token
            String token = JwtHelper.createToken(guest.getId(), name);
            map.put("token", token);
            //  返回前端url
            return "redirect:" + ConstantWxPropertiesUtils.BLOG_BASE_URL
                    + "/weixin/callback?token="+map.get("token")    //跳轉到前端頁面 ...
                    +"&openid="+map.get("openid")
                    +"&name="+URLEncoder.encode((String) map.get("name"), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

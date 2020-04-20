package com.yt.security.core.social.qq.api;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * @Description:
    参数	含义
    access_token	    可通过使用Authorization_Code获取Access_Token 或来获取。
                        access_token有3个月有效期。
    oauth_consumer_key	申请QQ登录成功后，分配给应用的appid
    openid	            用户的ID，与QQ号码一一对应。
                        可通过调用https://graph.qq.com/oauth2.0/me?access_token=**** 来获取。
    参考：https://wiki.open.qq.com/wiki/website/get_user_info
 * @Auther: yt
 * @Date: 2020/4/18 0018 14:38
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ{
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s&format=json";
    private String appId;
    private String openId;
    private ObjectMapper objectMapper = new ObjectMapper();
    //使用构造器传入参数获取openid
    public QQImpl(String accessToken,String appId){
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        //将URL_GET_OPENID中的%s替换成accessToken
        String url = String.format(URL_GET_OPENID, accessToken);
        //发出请求，接收返回值
        String result = getRestTemplate().getForObject(url,String.class);
        System.out.println(result);
        this.openId = StringUtils.substringBetween(result, "\"openid\":", "}");
    }
    @Override
    public QQUserInfo getUserInfo(){
        //有问题？？，参数有三个
        String url = String.format(URL_GET_USERINFO,appId,openId);
        String result = getRestTemplate().getForObject(url, String.class);
        System.out.println(result);
        try {
            return objectMapper.readValue(result,QQUserInfo.class);
        } catch (IOException e) {
            throw new RuntimeException("获取用户信息失败",e);
        }
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}

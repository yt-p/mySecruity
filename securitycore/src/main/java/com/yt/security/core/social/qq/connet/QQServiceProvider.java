package com.yt.security.core.social.qq.connet;

import com.yt.security.core.social.qq.api.QQ;
import com.yt.security.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @Description:
 * @Auther: yt
 * @Date: 2020/4/18 0018 15:35
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    private String appId;
    //appId appSecret 相当于用户密码
    public QQServiceProvider(String appId, String appSecret) {
        super(new OAuth2Template(appId,appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }
}

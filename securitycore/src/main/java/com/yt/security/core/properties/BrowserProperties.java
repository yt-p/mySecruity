package com.yt.security.core.properties;

import org.springframework.data.domain.PageRequest;

/**
 * @Description: 对web端配置 封装个性化认证流程
 * @Auther: yt
 * @Date: 2020/4/16 0016 10:00
 */
public class BrowserProperties {
    private String loginPage="/yt-signIn.html";
    private LoginType loginType = LoginType.JSON;
    private int rememberMeSeconds = 3600;
    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }
}

package com.yt.security.core.properties;

/**
 * @Description: 对配置 封装个性化认证流程
 * @Auther: yt
 * @Date: 2020/4/16 0016 10:00
 */
public class BrowserProperties {
    private String loginPage="/yt-signIn.html";

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}

package com.yt.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @Description: 对自定义配置读取
 * @Auther: yt
 * @Date: 2020/4/16 0016 09:59
 */

//自动扫描配置，自动注入到BrowserProperties，要保证配置文件与配置类属性名对应
//yt.security.browserProperties.loginPage=/demo-signIn.html
    //对应BrowserProperties类对象browserProperties名，再对应BrowserProperties成员属性loginPage名
@ConfigurationProperties(prefix = "yt.security")
public class SecurityProperties {
    private BrowserProperties browserProperties = new BrowserProperties();

    public BrowserProperties getBrowserProperties() {
        return browserProperties;
    }

    public void setBrowserProperties(BrowserProperties browserProperties) {
        this.browserProperties = browserProperties;
    }
}

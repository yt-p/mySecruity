package com.yt.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


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
    //认证表单
    private BrowserProperties browserProperties = new BrowserProperties();
    //图片验证码
    private ValidateCodeProperties code = new ValidateCodeProperties();
    public BrowserProperties getBrowserProperties() {
        return browserProperties;
    }

    public void setBrowserProperties(BrowserProperties browserProperties) {
        this.browserProperties = browserProperties;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

    public SecurityProperties() {
    }
}

package com.yt.security.browser;

import com.yt.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @Description:  身份认证配置
 * @Auther: yt
 * @Date: 2020/4/15 0015 17:10
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private AuthenticationSuccessHandler ytAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler ytAuthenticationFailureHandler;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //所有请求都需要表单身份认证
        //http.httpBasic()
        //表单验证登录
        http.formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(ytAuthenticationSuccessHandler)
                .failureHandler(ytAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require"
                        ,securityProperties.getBrowserProperties().getLoginPage()
                        ,"/code/image")
                //当访问/yt-signIn.html时不需要身份认证
                .permitAll()
                .anyRequest()
                .authenticated()
        .and()
        .csrf()
        .disable()
        ;
    }
}

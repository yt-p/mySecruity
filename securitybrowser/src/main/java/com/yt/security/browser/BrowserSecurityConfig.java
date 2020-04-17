package com.yt.security.browser;

import com.yt.security.browser.authentication.YtAuthenticationFailureHandler;
import com.yt.security.core.properties.SecurityProperties;
import com.yt.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

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
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;
    //对密码进行加密
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);//数据源
        //tokenRepository.setCreateTableOnStartup(true);//自动生成表存储用户,第一次需要开启
        return tokenRepository;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(ytAuthenticationFailureHandler);
        //出现无法解决的错误，调用会提示其成员属性 securityProperties null
        //无法在validateCodeFilter中获取到注入的securityProperties
        //导致图片验证码无法校验，其他配置不会影响，只能使用set方法塞入
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();
        //所有请求都需要表单身份认证
        //http.httpBasic()
        //表单验证登录
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(ytAuthenticationSuccessHandler)
                .failureHandler(ytAuthenticationFailureHandler)
                .and()
                .rememberMe()
                .userDetailsService(userDetailsService)
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowserProperties().getRememberMeSeconds())
                .and()
                .authorizeRequests()
                //不需验证的请求路径，防止出现redirect too many times死循环
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

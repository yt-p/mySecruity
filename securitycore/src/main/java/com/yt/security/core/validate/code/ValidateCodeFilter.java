package com.yt.security.core.validate.code;

import com.yt.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * @Auther: yt
 * @Date: 2020/4/16 0016 16:42
 */
//InitializingBean作用：当ValidateCodeFilter初始化的时候执行afterPropertiesSet()方法
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {//OncePerRequestFilter保证过滤器每次请求只会被调用一次
    private AuthenticationFailureHandler authenticationFailureHandler;
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    private Set<String> urls = new HashSet<>();
    private AntPathMatcher pathMatcher = new AntPathMatcher();
    //无法使用注解引入
    private SecurityProperties securityProperties;
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] strings = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrl(), ",");
        if(strings!=null){
            for(String url:strings){
                urls.add(url);
            }
        }
        urls.add("/authentication/form");
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean action=false;
        for (String url:urls){
            if(pathMatcher.match(url,request.getRequestURI())){
                action = true;
            }
        }
        if(action){
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                //出现异常不匹配，导致功能出问题 解决：导错包
                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
            }
        }
        //当请求路径为/authentication/form，且请求为POST请求时，才执行验证。（对应登录页面发送的请求）
        /*if (StringUtils.equals("/authentication/form",request.getRequestURI())&&
        StringUtils.equalsIgnoreCase(request.getMethod(),"post")) {
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                //出现异常不匹配，导致功能出问题
                //authenticationFailureHandler.onAuthenticationFailure(request,response,e);
                e.printStackTrace();
            }
        }*/
        filterChain.doFilter(request,response);
    }


    /**
     *@Description: 对验证码进行校验，比如是否过期等
     *@methodName: validate
     *@Param: [servletWebRequest]
     *@return: void
     *@Author: yt
     *@date: 2020/4/17 0017 8:57
    */
    private void validate(ServletWebRequest request) throws ValidateCodeException, ServletRequestBindingException {
        //从请求中取出之前存入session的验证码
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request,
                ValidateCodeController.SESSION_KEY);
        //获取form表单中用户输入的验证码
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");  //对应form表单中图片name
        System.out.println("表单提交的code："+codeInRequest);
        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码不能为空");
        }
        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }
        if (codeInSession.isExpired()) {
            sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
        //图片验证码原文链接：https://blog.csdn.net/ShotMoon/java/article/details/80424874
    }
    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}

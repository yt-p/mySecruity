package com.yt.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/**
 * @Description: 对用户验证
 * @Auther: yt
 * @Date: 2020/4/15 0015 18:04
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private Logger logger = LoggerFactory.getLogger(getClass());
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        logger.info("用户名："+userName);
        String s = passwordEncoder.encode("123456");
        logger.info("数据库密码："+s);
        return new User(userName,s,true,true,true,true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}

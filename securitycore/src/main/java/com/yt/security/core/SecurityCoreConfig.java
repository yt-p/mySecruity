package com.yt.security.core;

import com.yt.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 将自定义配置加载注入
 * @Auther: yt
 * @Date: 2020/4/16 0016 10:28
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}

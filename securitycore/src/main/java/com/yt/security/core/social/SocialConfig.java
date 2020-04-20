package com.yt.security.core.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;

import javax.sql.DataSource;

/**
 * @Description:
 * @Auther: yt
 * @Date: 2020/4/20 0020 09:16
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new JdbcUsersConnectionRepository(dataSource,connectionFactoryLocator, Encryptors.noOpText());
        }
    }


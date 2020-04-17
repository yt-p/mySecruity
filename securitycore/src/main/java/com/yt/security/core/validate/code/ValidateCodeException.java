package com.yt.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @Description:
 * @Auther: yt
 * @Date: 2020/4/16 0016 17:15
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg) {
        super(msg);
    }
}

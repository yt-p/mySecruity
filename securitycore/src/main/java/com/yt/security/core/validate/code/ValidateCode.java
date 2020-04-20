package com.yt.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Description:  短信验证码
 * @Auther: yt
 * @Date: 2020/4/16 0016 14:59
 */
public class ValidateCode {
    private String code;
    //到期时间
    private LocalDateTime expireTime;
    //expire 验证码有效时间 ||失效时间
    public ValidateCode(String code, int expire) {
        this.code = code;
        //expireTime===当前时间加上有效时间
        this.expireTime = LocalDateTime.now().plusSeconds(expire);
    }
    //验证码过期与否
    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expireTime);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}

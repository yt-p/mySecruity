package com.yt.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Description:
 * @Auther: yt
 * @Date: 2020/4/16 0016 14:59
 */
public class ImageCode {
    private BufferedImage image;
    private String code;
    //到期时间
    private LocalDateTime expireTime;
    //expire 验证码有效时间 ||失效时间
    public ImageCode(BufferedImage image, String code, int expire) {
        this.image = image;
        this.code = code;
        //expireTime===当前时间加上有效时间
        this.expireTime = LocalDateTime.now().plusSeconds(expire);
    }
    //验证码过期与否
    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expireTime);
    }
    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
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

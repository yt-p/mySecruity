package com.yt.security.core.validate.code;

import java.awt.image.BufferedImage;

/**
 * @Description: 图片验证码
 * @Auther: yt
 * @Date: 2020/4/16 0016 14:59
 */
public class ImageCode extends ValidateCode {
    private BufferedImage image;
    public ImageCode(BufferedImage image, String code, int expire) {
        super(code,expire);
        this.image = image;
    }
    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }


}

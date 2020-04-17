package com.yt.security.core.properties;

/**
 * @Description: 配置图片，短信验证码等
 * @Auther: yt
 * @Date: 2020/4/17 0017 09:23
 */
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }
}

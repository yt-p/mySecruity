package com.yt.security.core.validate.code;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:  如果需要跟换验证么逻辑，只需继承ValidateCodeGenerator接口
 * 重写createImageCode方法，再将其注解为@Component("imageCodeGenerator")即可替换默认逻辑
 * @Auther: yt
 * @Date: 2020/4/17 0017 14:13
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode createImageCode(HttpServletRequest request) {
        return null;
    }
}

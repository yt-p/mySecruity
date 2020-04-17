package com.yt.security.core.validate.code;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Auther: yt
 * @Date: 2020/4/17 0017 12:51
 */
public interface ValidateCodeGenerator {
    ImageCode createImageCode(HttpServletRequest request);
}

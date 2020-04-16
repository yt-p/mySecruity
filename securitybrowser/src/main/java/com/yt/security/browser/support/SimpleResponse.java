package com.yt.security.browser.support;

/**
 * @Description: 封装
 * @Auther: yt
 * @Date: 2020/4/16 0016 09:44
 */
public class SimpleResponse {
    private Object content;

    public SimpleResponse() {
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public SimpleResponse(Object content) {
        this.content = content;
    }
}

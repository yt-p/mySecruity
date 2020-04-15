package com.yt.async;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: yt
 * @Date: 2020/4/15 0015 14:54
 */
@Component
public class DeferredResultHolder {
    private Map<String, DeferredResult> map = new HashMap<>();

    public void setMap(Map<String, DeferredResult> map) {
        this.map = map;
    }

    public Map<String,DeferredResult> getMap(){
        return map;
    }
}

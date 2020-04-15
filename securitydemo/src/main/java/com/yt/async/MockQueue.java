package com.yt.async;

import org.springframework.stereotype.Component;

/**
 * @Description: 模仿一个消息队列
 * @Auther: yt
 * @Date: 2020/4/15 0015 14:49
 */
@Component
public class MockQueue {
    private String placeOrder;
    private String completeOrder;

    public MockQueue() {
    }

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) throws InterruptedException {
        System.out.println("接到下单请求"+placeOrder);
        Thread.sleep(1000);
        this.completeOrder = placeOrder;
        System.out.println("下单请求处理完毕"+completeOrder);
    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}

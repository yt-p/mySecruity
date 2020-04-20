package com.yt.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Auther: yt
 * @Date: 2020/4/15 0015 14:04
 */
@Aspect  //声明只是一个切面
@Component
public class TimeAspect {
    @Around("execution(* com.yt.controller.UserController.*(..))") //设置切入点 第二个*表示方法，(..)表示方法的所有参数
    public Object HandlerControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        Object proceed = pjp.proceed();
        return proceed;
    }

}

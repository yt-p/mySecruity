package com.yt.controller;

import com.yt.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Auther: yt
 * @Date: 2020/4/15 0015 09:28
 */
@ControllerAdvice
public class ControllerExceptionHandler  {
    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> HandlerUserNotExistException(UserNotExistException ue){
        Map<String, Object> result = new HashMap<>();
        result.put("id", ue.getId());
        result.put("message", ue.getMessage());
        return result;
    }
}

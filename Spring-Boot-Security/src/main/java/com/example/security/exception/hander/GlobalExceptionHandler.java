package com.example.security.exception.hander;

import com.example.security.data.ApiResponse;
import com.example.security.data.Status;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: DS
 * @Date: 2023/11/20 14:40
 * @Description:
 **/

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ApiResponse handler(Exception ex) {
        return ApiResponse.of(Status.ERROR.getCode(),ex.getMessage(),"ds");
    }
}

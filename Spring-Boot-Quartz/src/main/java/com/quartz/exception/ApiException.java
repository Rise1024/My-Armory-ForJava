package com.quartz.exception;

import lombok.Getter;

/**
 * @Auther: zds
 * @Date: 2023/07/13/11:45
 * @Description:
 */
@Getter
public class ApiException extends RuntimeException {
    private String message;

    public ApiException(String message) {
        super(message);
        this.message = message;
    }
}

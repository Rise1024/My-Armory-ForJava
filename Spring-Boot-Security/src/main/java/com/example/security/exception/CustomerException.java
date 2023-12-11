package com.example.security.exception;

import lombok.Getter;

/**
 * @Author: DS
 * @Date: 2023/11/20 17:21
 * @Description:
 **/
@Getter
public class CustomerException extends RuntimeException {

    private final String msg;

    public CustomerException(String msg) {
        this.msg = msg;
    }
}

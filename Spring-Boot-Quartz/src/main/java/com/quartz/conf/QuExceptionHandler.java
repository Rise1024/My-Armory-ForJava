package com.quartz.conf;

import com.quartz.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
@ControllerAdvice
@ResponseBody
public class QuExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public String handleApiException(ApiException e) {
        log.error("ExceptionHandler hander this");
        return e.getMessage();
    }


}

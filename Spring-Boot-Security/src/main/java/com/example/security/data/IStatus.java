package com.example.security.data;

/**
 * @Author: DS
 * @Date: 2023/11/22 10:00
 * @Description:
 **/
public interface IStatus {

    /**
     * 状态码
     *
     * @return 状态码
     */
    Integer getCode();

    /**
     * 返回信息
     *
     * @return 返回信息
     */
    String getMessage();

}

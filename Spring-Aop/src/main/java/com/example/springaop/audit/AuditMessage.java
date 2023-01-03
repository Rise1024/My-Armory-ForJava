package com.example.springaop.audit;

import lombok.Data;

/**
 * @author zds
 * @Description
 * @createTime 2021/10/18 18:36
 */

@Data
public class AuditMessage {

    private Long traceId;

    private String operate_time;

    private String operate_type;

    private String operate_object;

    private String operation;

    private String operator;

    private String service_name;

    private String operate_url;

    private String accessor_ip;

    private String result;

    private String detail;

}

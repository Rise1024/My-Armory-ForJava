package com.example.springaop.audit;

/**
 * @author zds
 * @Description
 * @createTime 2021/9/29 14:27
 */
public enum LogNameEnum {

    AUDITLOG("auditlog") ;

    private String logName;

    LogNameEnum(String logName) {
        this.logName = logName;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }
}

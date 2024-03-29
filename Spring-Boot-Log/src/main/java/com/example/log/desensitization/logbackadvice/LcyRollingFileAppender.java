package com.example.log.desensitization.logbackadvice;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.rolling.RollingFileAppender;

public class LcyRollingFileAppender extends RollingFileAppender {

    @Override
    protected void subAppend(Object event) {
        DesensitizationAppender appender = new DesensitizationAppender();
        appender.operation((LoggingEvent)event);
        super.subAppend(event);
    }
}

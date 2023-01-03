package com.example.log.desensitization.logbackadvice;

import ch.qos.logback.classic.spi.LoggingEvent;
import com.example.log.desensitization.utils.DesensitizationUtil;

import java.lang.reflect.Field;


public class DesensitizationAppender {

    private static final String MESSAGE = "message";

    private static final String FORMATTED_MESSAGE = "formattedMessage";

    public void operation(LoggingEvent event) {
        if (event.getArgumentArray() != null) {
            // 获取格式化后的Message
            String eventFormattedMessage = event.getFormattedMessage();

            DesensitizationUtil util = new DesensitizationUtil();
            // 获取替换后的日志信息
            String changeMessage = util.customChange(eventFormattedMessage);
            if (!(null == changeMessage || "".equals(changeMessage))) {
                try {
                    // 利用反射的方式，将替换后的日志设置到原event对象中去
                    Class<? extends LoggingEvent> eventClass = event.getClass();
                    // 保险起见，将message和formattedMessage都替换了
                    Field message = eventClass.getDeclaredField(MESSAGE);
                    message.setAccessible(true);
                    message.set(event, changeMessage);
                    Field formattedMessage = eventClass.getDeclaredField(FORMATTED_MESSAGE);
                    formattedMessage.setAccessible(true);
                    formattedMessage.set(event, changeMessage);
                } catch (IllegalAccessException | NoSuchFieldException e) {
                }
            }

        }
    }
}

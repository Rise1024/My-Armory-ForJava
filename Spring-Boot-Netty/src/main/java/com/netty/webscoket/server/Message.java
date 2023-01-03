package com.netty.webscoket.server;


/**
 * @author zds
 * @Description
 * @createTime 2021/8/24 14:28
 */
public class Message {
    /**
     * 消息类型
     */
    private Integer type;
    /**
     * 聊天消息
     */
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 扩展消息字段
     */
    private String ext;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Object getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type=" + type +
                ", ext=" + ext +
                '}';
    }

}



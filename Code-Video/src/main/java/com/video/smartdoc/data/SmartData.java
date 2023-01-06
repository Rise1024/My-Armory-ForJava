package com.video.smartdoc.data;

import lombok.Data;

/**
 * @Auther: zds
 * @Date: 2023/01/05/22:56
 * @Description:
 */
@Data
public class SmartData {

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private int age;

    /**
     * 是否男孩
     */
    private boolean boy;

    public SmartData(String name, int age, boolean boy) {
        this.name = name;
        this.age = age;
        this.boy = boy;
    }
}

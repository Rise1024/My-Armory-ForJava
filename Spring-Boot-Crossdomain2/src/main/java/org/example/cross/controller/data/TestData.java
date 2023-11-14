package org.example.cross.controller.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: DS
 * @Date: 2023/10/12 14:30
 * @Description:
 **/

public class TestData {
    private String name;
    private Integer age;

    public TestData() {
    }
    public TestData(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

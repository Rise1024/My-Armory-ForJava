package com.example.springbean.regist.domain;

import lombok.*;

/**
 * @author zds
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
// @Component
public class User {
    private String name;
    private Integer age;
}

package com.jdk17.jdk16;

import java.util.Objects;

/**
 * @param id
 * @param name
 * @param age
 * 一个POJO对象包括一系列的类成员变量和相应的Getter和Setter函数，
 * 虽然可以通过工具或者IDE直接生成，
 * 但是修改维护起来还是非常麻烦的
 * 但是如果要实现相应的hashcode和equals方法，
 * 需要手工去写
 */
public record User(long id, String name, int age) {

}

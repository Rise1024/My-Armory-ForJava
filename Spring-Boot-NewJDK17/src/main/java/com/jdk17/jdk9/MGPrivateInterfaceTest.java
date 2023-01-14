package com.jdk17.jdk9;

/**
 * Java 8支持在接口中编写default方法，
 * 而从 Java 9 开始，可以在接口中包含私有方法
 */
public interface MGPrivateInterfaceTest {
    default void defaultMethod() {
        privateMethod();
    }
    private void privateMethod() {
    }
}
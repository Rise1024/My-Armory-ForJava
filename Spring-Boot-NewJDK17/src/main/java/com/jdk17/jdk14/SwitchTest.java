package com.jdk17.jdk14;

/**
 * @Auther: zds
 * @Date: 2023/01/14/16:14
 * @Description:
 * 在 Java 12 和 13 中陆续提供了功能预览，而在 Java 14 中成为标准功能
 */
public class SwitchTest {

    /**
     * 在JDK8中获取switch返回值方式
     */
    public static int getCodeInJDK8(String mode) {
        int i = 0;
        switch (mode) {
            case "a", "b":
                i = 1;
                break;
            case "c":
                i = 3;
                break;
            default:
                i = 3;
                break;
        }

        return i;
    }
    /**
     * 在JDK17中获取switch返回值方式
     */
    private static int getCodeInJDK17(String mode) {
        int result = switch (mode) {
            case "a", "b":
                yield 1; //使用 yield 提供返回值
            case "c":
                yield 2;
            default:
                yield 3;
        };
        return result;
    }

    private static int getCodeInJDK17_1(String mode) {
        int result = switch (mode) {
            case "a", "b" -> 1;
            case "c" -> 2;
            default -> -1;
        };
        return result;
    }
    public static void main(String[] args) {
        getCodeInJDK17("a");
    }
}

package com.jdk17.jdk15;

/**
 * @Auther: zds
 * @Date: 2023/01/14/16:23
 * @Description:
 * 文本块功能最开始在 Java 13 和 14 中提供预览，
 * 最终在 Java 15 成型
 */
public class TxtTest {
    /**
     * 使用JDK8返回HTML文本
     * 需要一堆拼接符号
     */
    public static final String getHtmlTextInJDK8() {
        return "<html>\n" +
                "  <body>\n" +
                "    <p>Hello, world</p>\n" +
                "  </body>\n" +
                "</html>";
    }

    /**
     * 使用JDK17返回HTML文本
     */
    public static final String getHtmlTextInJDK17() {
        return """
                    <html>
                      <body>
                        <p>Hello, world</p>
                      </body>
                    </html>
                """;
    }

    public static void main(String[] args) {
        getHtmlTextInJDK8();
        getHtmlTextInJDK17();
    }
}

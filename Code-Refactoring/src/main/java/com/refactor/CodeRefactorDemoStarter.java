package com.refactor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
public class CodeRefactorDemoStarter {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(CodeRefactorDemoStarter.class, args);
    }

}

package com.example.springbean.regist.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author zds
 */
public class MyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{
                "cc.zds.demo.domain.Apple",
                "cc.zds.demo.domain.Banana",
                "cc.zds.demo.domain.Watermelon"
        };
    }
}

package com.video.jvm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: zds
 * @Date: 2023/01/20/00:03
 * @Description:
 */

@Data
public class OomData {
    private Integer id;
    private String name;

    public OomData(int i, String name) {
        this.id=i;
        this.name=name;
    }
}

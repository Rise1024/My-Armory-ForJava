package com.base.extendstest.functiona.extendstest.execption;

/**
 * @Auther: zds
 * @Date: 2023/02/07/17:28
 * @Description:
 */
public class TestExceptionService {

    private String test(Integer a){
       //继承RuntimeException不需要捕获
        if (a>0){
            throw new RuntimeTestexecption();
        }
        //继承Exception需要捕获
        if (a>10){
            try {
                throw new TestExecption();
            } catch (TestExecption e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }


    public static void main(String[] args) {

    }
}

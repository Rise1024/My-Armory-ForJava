package com.refactor.factory;

import com.refactor.factory.Calculate;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: zds
 * @Date: 2022/11/03/14:31
 * @Description:
 *
 * --------------断言测试------------------------------------------------------
 *
 *    //第一个参数是期望值，后一个值是实际需要判断的结果。在后面是设置的如果不满足断言报错的内容
 *     Assertions.assertEquals(3,result,"这两个值不相同");
 *     Assertions.assertNotEquals(4,result,"这两个值一样");
 *     Assertions.assertSame(3,result);
 *     Assertions.assertNotSame(32,result);
 *     //断言实际值
 *     Assertions.assertNull(null);
 *     Assertions.assertNotNull(result);
 *     //断言实际值操作
 *     Assertions.assertTrue(result > 0);
 *     Assertions.assertFalse(result == 4);
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class CalculateTest {
    @Autowired
    Calculate calculate;

    @Test
    void calculateUsingFactory() {
        int add = calculate.calculateUsingFactory(2, 3, "add");
        System.out.println(add);
//        Assertions.assertNull(add,"add result"+add);    }
    }
}
package com.es.servvice;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.CreateResponse;
import com.es.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @Auther: zds
 * @Date: 2023/02/02/17:39
 * @Description:
 */
@SpringBootTest
public class EsDataService {

    @Autowired
    ElasticsearchClient client;

    @Test
    void add() throws IOException {
        // 创建一个需要保存至ES的对象
        User user=new User();
        user.setAge(12);
        user.setName("sd");;
         // 构建一个创建Doc的请求
//        CreateResponse createResponse = client.create(e->e.index("db").id("1003").document(user));
        CreateResponse createResponse = client.create(e->e.index("db").type("_tables").id("1003").document(user));

        // 打印请求结果
        System.out.println(createResponse.result());

    }

    @Test
    void query() throws IOException {
        // 打印请求结果
//        System.out.println(createResponse.result());

    }


}

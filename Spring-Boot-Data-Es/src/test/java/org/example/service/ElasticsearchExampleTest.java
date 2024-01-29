package org.example.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchExampleTest {
    @Autowired
    private ElasticsearchExample elasticsearchExample;

    @Test
   public void bulkSave() {
        for (int i = 0; i < 100000; i++) {
            elasticsearchExample.bulkSave(4);
            if (i%1000==0){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
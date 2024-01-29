package com.es.servvice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsDataServiceTest {
    @Autowired
    private EsDataService esDataService;

    @Test
    public void esSave() {
        try {
            esDataService.add(11);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void esGet() {
        try {
            System.out.println(esDataService.get());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
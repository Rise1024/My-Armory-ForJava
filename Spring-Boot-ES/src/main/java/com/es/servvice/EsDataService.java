package com.es.servvice;


import com.alibaba.fastjson.JSON;
import com.es.data.Message;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.Cancellable;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * @Auther: zds
 * @Date: 2023/02/02/17:39
 * @Description:
 */
@Service
public class EsDataService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public void add(Integer id) throws IOException {
        String string = UUID.randomUUID().toString();
        int tenantId = id;
        int organId = id;
        Message message = new Message("webim", "agent", tenantId, organId, string, string, string, new Date().getTime(), new Date().getTime());
        //转为json
        String toJSONString = JSON.toJSONString(message);

        IndexRequest indexrequest = new IndexRequest("message", "message");
        //指定id
        indexrequest.id(message.getSessionId());
        //添加数据
        indexrequest.source(toJSONString, XContentType.JSON);
        //设置Elasticsearch数据刷新策略
        indexrequest.setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE);
        try {
            IndexResponse index = restHighLevelClient.index(indexrequest, RequestOptions.DEFAULT);
            int status = index.status().getStatus();
            System.out.println(status);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }

    }


    public String get() throws IOException {
        GetRequest getRequest = new GetRequest("message", "message","e717352d-3796-476f-9282-bc3f26dd87fd");
        GetResponse documentFields = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        String sourceAsString = documentFields.getSourceAsString();
        return sourceAsString;
    }


    public void get1() {
        GetRequest a = new GetRequest("message", "e717352d-3796-476f-9282-bc3f26dd87fd");
        Cancellable async = restHighLevelClient.getAsync(a, RequestOptions.DEFAULT, new ActionListener<GetResponse>() {
            @Override
            public void onResponse(GetResponse documentFields) {
                String sourceAsString = documentFields.getSourceAsString();
                System.out.println(sourceAsString);
            }

            @Override
            public void onFailure(Exception e) {
            }
        });
    }

}
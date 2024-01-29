package org.example.service;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.example.data.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ElasticsearchExample {

    @Autowired
    private Client client;


    /** bulk操作，批量索引数据*/

    public void bulkSave(Integer id)  {
        String string = UUID.randomUUID().toString();
        int tenantId=id;
        int organId=id;
        Message message=new Message("webim","agent",tenantId,organId,string,string,string,new Date().getTime(),new Date().getTime());
        Map map = JSON.parseObject(JSON.toJSONString(message), Map.class);
        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
        bulkRequestBuilder.add(client.prepareIndex("message", "message", string).setSource(map));
        BulkResponse bulkItemResponses = bulkRequestBuilder.get();
        System.out.println(bulkItemResponses.hasFailures());
    }

}

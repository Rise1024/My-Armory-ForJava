package com.es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.json.JsonData;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import com.es.data.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Auther: zds
 * @Date: 2022/11/03/16:57
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class SpringESDemoStarterTest {

    @Autowired
    private ElasticsearchClient client;

    @Test
    public void createTest() throws IOException {

        //写法比RestHighLevelClient更加简洁
        CreateIndexResponse indexResponse = client.indices().create(c -> c.index("user"));
    }


    @Test
    public void existsTest() throws IOException {
        BooleanResponse booleanResponse = client.indices().exists(e -> e.index("user"));
        System.out.println(booleanResponse.value());
    }

    @Test
    public void addDocumentTest() throws IOException {

        User user = new User("user1", 10);
        IndexResponse indexResponse = client.index(i -> i
                .index("user")
                //设置id
                .id("1")
                //传入user对象
                .document(user));

    }

    @Test
    public void getDocumentTest() throws IOException {
        GetResponse<User> getResponse = client.get(g -> g
                        .index("user")
                        .id("1")
                , User.class
        );
        System.out.println(getResponse.source());
    }

    @Test
    public void bulkTest() throws IOException {
        List<User> userList = new ArrayList<>();
        userList.add(new User("user1", 11));
        userList.add(new User("user2", 12));
        userList.add(new User("user3", 13));
        userList.add(new User("user4", 14));
        userList.add(new User("user5", 15));
        List<BulkOperation> bulkOperationArrayList = new ArrayList<>();
        //遍历添加到bulk中
        for (User user : userList) {
            bulkOperationArrayList.add(BulkOperation.of(o -> o.index(i -> i.document(user))));
        }

        BulkResponse bulkResponse = client.bulk(b -> b.index("user")
                .operations(bulkOperationArrayList));

    }

    //term的查询是代表完全匹配，搜索之前不会对你搜索的关键字进行分词，对你的关键字去文档分词库中去匹配内容。
    @Test
    public void searchTestTerm() throws IOException {
        SearchResponse<User> search = client.search(s -> s
                .index("user")
                //查询name字段包含hello的document(不使用分词器精确查找)
                .query(q -> q
                        .term(t -> t
                                .field("name")
                                .value(v -> v.stringValue("user5"))
                        ))
                //分页查询，从第0页开始查询3个document
                .from(0)
                .size(3)
                //按age降序排序
                .sort(f -> f.field(o -> o.field("age").order(SortOrder.Desc))), User.class
        );
        for (Hit<User> hit : search.hits().hits()) {
            System.out.println(hit.source());
        }
    }

    /*terms和term的查询机制是一样的，都不会将指定的查询关键字进行分词，直接去分词库中匹配，找到相应文档内容。
    terms是在针对一个字段包含多个值的时候使用。
    term：where name = user5；
    terms：where name = user5 or province = ？or province = ？*/
    @Test
    public void searchTestTerms() throws IOException {

        SearchResponse<User> search = client.search(s -> s
                .index("user")
                //查询name字段包含hello的document(不使用分词器精确查找)
                .query(q -> q
                        .terms(t -> t
                                .field("name")
                                .terms(v -> v.value(Stream.of(FieldValue.of("user5"), FieldValue.of("user4")).collect(Collectors.toList())))

                        ))
                //分页查询，从第0页开始查询3个document
                .from(0)
                .size(3)
                //按age降序排序
                .sort(f -> f.field(o -> o.field("age").order(SortOrder.Desc))), User.class
        );
        for (Hit<User> hit : search.hits().hits()) {
            System.out.println(hit.source());
        }
    }

    @Test
    public void searchTestRange() throws IOException {

        SearchResponse<User> search = client.search(s -> s
                .index("user")
                //查询name字段包含hello的document(不使用分词器精确查找)
                .query(q -> q
                        .range(
                                v -> v
                                        .field("age")
                                        .from(String.valueOf(13))
                                        .to(String.valueOf(15))
                        ))
                //分页查询，从第0页开始查询3个document
                .from(0)
                .size(3)
                //按age降序排序
                .sort(f -> f.field(o -> o.field("age").order(SortOrder.Desc))), User.class
        );
        for (Hit<User> hit : search.hits().hits()) {
            System.out.println(hit.source());
        }
    }

    @Test
    public void searchTestRangenoWith() throws IOException {

        SearchResponse<User> search = client.search(s -> s
                .index("user")
                //查询name字段包含hello的document(不使用分词器精确查找)
                .query(q -> q
                        .range(
                                v -> v
                                        .field("age")
                                        .from(String.valueOf(13))
                                        .to(String.valueOf(15))

                        ))
                //分页查询，从第0页开始查询3个document
                .from(0)
                .size(3)
                //按age降序排序
                .sort(f -> f.field(o -> o.field("age").order(SortOrder.Desc))), User.class
        );
        for (Hit<User> hit : search.hits().hits()) {
            System.out.println(hit.source());
        }
    }


    /**
     * 范围查询, lt:小于，gt:大于
     * @throws IOException
     */

    @Test
    public void searchTestltgt() throws IOException {

        SearchResponse<User> search = client.search(s -> s
                .index("user")
                //查询name字段包含hello的document(不使用分词器精确查找)
                .query(q -> q
                        .range(
                                v -> v
                                        .field("age")
                                        .gt(JsonData.fromJson("13"))
                                        .lt(JsonData.fromJson("15"))
                                        .relation(RangeRelation.Contains)
                        ))
                //分页查询，从第0页开始查询3个document
                .from(0)
                .size(3)
                //按age降序排序
                .sort(f -> f.field(o -> o.field("age").order(SortOrder.Desc))), User.class
        );
        for (Hit<User> hit : search.hits().hits()) {
            System.out.println(hit.source());
        }
    }

    /**
     * 模糊查询
     * @throws IOException
     */
    @Test
    public void searchTest() throws IOException {

        SearchResponse<User> search = client.search(s -> s
                .index("user")
                //查询name字段包含hello的document(不使用分词器精确查找)
                .query(q -> q
                        .wildcard(e->e
                                .field("name")
                                .wildcard("user")
                        ))
                //分页查询，从第0页开始查询3个document
                .from(0)
                .size(3)
                //按age降序排序
                .sort(f -> f.field(o -> o.field("age").order(SortOrder.Desc))), User.class
        );
        for (Hit<User> hit : search.hits().hits()) {
            System.out.println(hit.source());
        }
    }

}
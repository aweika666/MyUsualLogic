package com.aweika;


import com.alibaba.fastjson.JSON;
import com.aweika.entity.User;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@SpringBootTest
class AweikaEsApiApplicationTests {

    @Autowired
    private RestHighLevelClient esClient;

    //创建索引
    @Test
    void testCreatIndex() throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("aweika_test");
        /*createIndexRequest.mapping(new HashMap<String,String>(){{put("name","ik_smart");}});*/
        CreateIndexResponse createIndexResponse = esClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse.isAcknowledged());
    }

    //获取索引,判断是否存在
    @Test
    void testExistIndex() throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest("aweika_test");
        boolean exists = esClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    //删除索引
    @Test
    void testDeleteIndex() throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("aweika_test");
        AcknowledgedResponse acknowledgedResponse = esClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        System.out.println(acknowledgedResponse.isAcknowledged());
    }

    /*
    对文档的操作
     */

    //添加数据
    @Test
    void testAddDoc() throws IOException{
        User user = new User("狂神说java", 23);
        //创建索引请求
        IndexRequest indexRequest = new IndexRequest("aweika_test");
        //数据id
        indexRequest.id("1");
        indexRequest.source(JSON.toJSONString(user),XContentType.JSON);
        //发送请求
        IndexResponse index = esClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(index.status());
    }

    //测试数据是否存在
    @Test
    void testDocExist() throws IOException{
        GetRequest getRequest = new GetRequest("aweika_test", "1");
        boolean exists = esClient.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    //获取数据
    @Test
    void testGetDoc() throws IOException{
        GetRequest getRequest = new GetRequest("aweika_test", "1");
        GetResponse getResponse = esClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(getResponse.getSourceAsString());
    }


    //修改数据
    @Test
    void testUpdateDoc() throws IOException{
        UpdateRequest updateRequest = new UpdateRequest("aweika_test", "1");
        User user = new User("狂神说java是猪皮", 12);
        updateRequest.doc(JSON.toJSONString(user),XContentType.JSON);
        UpdateResponse updateResponse = esClient.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(updateResponse.status());
    }

    //删除数据
    @Test
    void testDeleteDoc() throws IOException{
        DeleteRequest deleteRequest = new DeleteRequest("user_entity");
        DeleteResponse delete = esClient.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(delete.status());
    }

    /**
     * 批量操作
     */

    @Test
    void testBulkRequest() throws IOException{
        BulkRequest bulkRequest = new BulkRequest();
        //请求超时时间
        bulkRequest.timeout("10s");

        ArrayList<User> users = new ArrayList<>();
        users.add(new User("狂神说java 1",1));
        users.add(new User("狂神说java 2",1));
        users.add(new User("狂神说java 3",1));
        users.add(new User("狂神说java 4",1));
        users.add(new User("狂神说java 5",1));
        users.add(new User("狂神说java 6",1));
        int i = 1;
        for (User user : users) {
            //删除 修改 就变动这里
           // bulkRequest.add(new IndexRequest("aweika_test").id("" + i++).source(JSON.toJSONString(user),XContentType.JSON));
           // 删除
           bulkRequest.add(new DeleteRequest("aweika_test","" + i++));
        }

        BulkResponse bulkResponse = esClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulkResponse.hasFailures());
    }

    /**
     * 条件查询
     *
     */
    @Test
    void testSearch() throws IOException{
        SearchRequest searchRequest = new SearchRequest("aweika_test");
        //创建搜索条件对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", "狂神说");
        searchSourceBuilder.query(matchQueryBuilder);

        searchRequest.source(searchSourceBuilder);

        SearchResponse search = esClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = search.getHits();
        for (SearchHit hit : hits.getHits()) {
            System.out.println(hit.getSourceAsString());
        }
    }


}

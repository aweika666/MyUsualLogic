package com.aweika;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author: Michael
 * @date: 2020/8/17
 * @description:
 */
@RestController
@RequestMapping
public class TestController {

    @Autowired
    private RestHighLevelClient esClient;


    @PostMapping("testCreatIndex")
    public Object testCreatIndex() throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("aweikaceshiApi");
        CreateIndexResponse createIndexResponse = esClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse.isAcknowledged());
        return "ok";
    }

}

package com.aweika.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Michael
 * @date: 2020/8/17
 * @description:
 */
//@Configuration
public class ElasticSearchConfig {

    @Bean
    public RestHighLevelClient esClient(){
        return new RestHighLevelClient(RestClient.builder(new HttpHost("10.1.2.196",9200,"http")));
    }
}

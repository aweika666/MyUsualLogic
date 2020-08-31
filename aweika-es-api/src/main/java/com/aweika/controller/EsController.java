package com.aweika.controller;

import com.aweika.common.response.WebResponse;
import com.aweika.entity.UserEntity;
import com.aweika.esService.EsUserService;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author: Michael
 * @date: 2020/8/19
 * @description:
 */
@RestController
public class EsController {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;


    @Autowired
    private EsUserService esUserEntityService;

    private String[] names={"诸葛亮","曹操","李白","韩信","赵云","小乔","狄仁杰","李四","诸小明","王五"};
    private String[] infos={"我来自中国的一个小乡村，地处湖南省","我来自中国的一个大城市，名叫上海，人们称作魔都"
            ,"我来自东北，家住大囤里，一口大碴子话"};


    @GetMapping("saveMapping")
    public Object saveMapping(){
        //添加索引mapping    索引会自动创建但mapping自只用默认的这会导致分词器不生效 所以这里我们手动导入mapping
        elasticsearchTemplate.putMapping(UserEntity.class);
        return WebResponse.resSuccess("成功", 1);
    }

    @GetMapping("saveUserEntity")
    public WebResponse saveUserEntity(){
            /*
            添加数据
             */
            Random random = new Random();
            List<UserEntity> users = new ArrayList<>();
            for (int i=0;i<20;i++){
                UserEntity user = new UserEntity();
                //user.setUserId(i);
                user.setId(i);
                user.setName(names[random.nextInt(9)]);
                user.setAge(random.nextInt(40)+i);
                user.setInfo(infos[random.nextInt(2)]);
                users.add(user);
            }
            Iterable<UserEntity> userEntities = esUserEntityService.saveAll(users);
            return WebResponse.resSuccess("成功",userEntities);
        }


    @GetMapping("getDataById")
    public WebResponse getDataById(Integer id){
        return WebResponse.resSuccess("成功",esUserEntityService.findById(id));
    }

    @GetMapping("getDataByName")
    public WebResponse getDataByName(String name){
        return WebResponse.resSuccess("成功",esUserEntityService.findByName(name));
    }

    @GetMapping("getDataByInfo")
    public WebResponse getDataByInfo(String info){
        return WebResponse.resSuccess("成功",esUserEntityService.findByInfo(info));
    }

    //分页查询
    @GetMapping("getAllDataByPage")
    public WebResponse getAllDataByPage(){
        //本该传入page和size，这里为了方便就直接写死了
        Pageable page = PageRequest.of(0,10, Sort.Direction.ASC,"id");
        Page<UserEntity> all = esUserEntityService.findAll(page);
        return WebResponse.resSuccess("成功",all.getContent());
    }


    /*
    查询高亮显示
     */

    @GetMapping("getHightByUser")
    public WebResponse getHightByUser(String value){
        //根据一个值查询多个字段  并高亮显示  这里的查询是取并集，即多个字段只需要有一个字段满足即可
        //需要查询的字段
        BoolQueryBuilder boolQueryBuilder= QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("info",value))
                .should(QueryBuilders.matchQuery("name",value));
        //构建高亮查询
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withHighlightFields(
                        new HighlightBuilder.Field("info")
                        ,new HighlightBuilder.Field("name"))
                .withHighlightBuilder(new HighlightBuilder().preTags("<span style='color:red'>").postTags("</span>"))
                .withPageable(PageRequest.of(0, 3)) //本该传入page和size，这里为了方便就直接写死了
                .build();
        //查询
        SearchHits<UserEntity> search = elasticsearchTemplate.search(searchQuery, UserEntity.class);
        //得到查询返回的内容
        List<SearchHit<UserEntity>> searchHits = search.getSearchHits();
        //设置一个最后需要返回的实体类集合
        List<UserEntity> users = new ArrayList<>();
        //遍历返回的内容进行处理
        for(SearchHit<UserEntity> searchHit:searchHits){
            //高亮的内容
            Map<String, List<String>> highlightFields = searchHit.getHighlightFields();
            //将高亮的内容填充到content中
            searchHit.getContent().setName(highlightFields.get("name")==null ? searchHit.getContent().getName():highlightFields.get("name").get(0));
            searchHit.getContent().setInfo(highlightFields.get("info")==null ? searchHit.getContent().getInfo():highlightFields.get("info").get(0));
            //放到实体类中
            users.add(searchHit.getContent());
        }
        return WebResponse.resSuccess("成功",users);
    }

}

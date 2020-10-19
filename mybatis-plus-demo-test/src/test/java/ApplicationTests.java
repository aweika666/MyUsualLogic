import com.aweika.MybatisPlusApplication;
import com.aweika.entity.User;
import com.aweika.mapper.UserMapper;
import com.aweika.util.CamelUnderlineUtil;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @author: Aweika_Fang
 * @date: 2020/9/7
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={MybatisPlusApplication.class})// 指定启动类
public class ApplicationTests {


    @Autowired
    private UserMapper userMapper;



    @Test
    public void insertByOwn(){
        //insert into user ( user_name ) values ( 'aaa测试' );
        //自己写的insert语句, id键如果想被自动填充,则不能使用<if>标签
        User user = new User();
        user.setUserName("aaa测试");
        userMapper.insertByOwn(user);
        System.out.println(user.getId());
        /*
INSERT INTO user ( id, user_age, user_email ) VALUES ( 1303173849969344514, 12, '89908892333@qq.com' );
         */
    }

    /*-----------------------------------------------------*/
    /*
    insert方法在插入时，会根据实体类的每个属性进行非空判断，只有非空的属性所对应的字段才会出现在SQL语句中。
    */
    @Test
    public void insert(){

        User user = new User();
        user.setUserAge(12).setUserEmail("89908892333@qq.com");
        Integer insert = userMapper.insert(user);
        System.out.println(user.getId());
        /*
INSERT INTO user ( id, user_age, user_email ) VALUES ( 1303173849969344514, 12, '89908892333@qq.com' );
         */
    }

    /*
    insertAllColumn方法在插入时，不管属性是否为空，属性所对应的字段都会出现.
     */
    @Test
    public void insertAllColumn(){
        User user = new User();
        user.setUserAge(13).setUserName("老方");
        Integer insert = userMapper.insert(user);
        System.out.println(insert);
    }


    /*
    没有传值的字段不会进行更新
     */
    @Test
    public void updateById(){
        User user = new User(){{setId(1303188042126798850L);setUserName("老陈");}};
        userMapper.updateById(user);
    }

    /*
    对所有字段进行更新,没有传值的列会更新为null
     */
    @Test
    public void updateAllColumnById(){
        User user = new User(){{setId(1303188042126798850L);/*setUserName("老陈");*/}};
        userMapper.updateById(user);
    }

    /*
    根据id查询
     */
    @Test
    public void selectById(){
        //User user = new User(){{setId(1303941279350480898L);setUserName("老陈");}};

        User user1 = new User();
        user1 = user1.setId(1303941279350480898L).selectById();
       // User userReturn = userMapper.selectById(user);
        System.out.println(user1);
        //System.out.println(userReturn);
    }


    /*
    查询一条结果,根据传入对象字段不为null的作为搜索字段;
    返回结果有多条则报错
     */
    @Test
    public void selectOne(){
        User user1 = new User(){{setId(1303188042126798850L);setUserName("老陈");}};
        User user2 = new User(){{setId(1303187842339516417L);setUserEmail("aaa");}};
        User user1Return = userMapper.selectOne(user1);
        System.out.println(user1Return);
        User user2Return = userMapper.selectOne(user2);
        System.out.println(user2Return);
        //----  返回结果有多条则报错
        User user3Return =userMapper.selectOne(new User(){{setUserName("老方");}});
    }

    /*
    查询条件用map集合封装，columnMap，写的是数据表中的列名，而非实体类的属性名。
    比如属性名为lastName，数据表中字段为last_name，这里应该写的是last_name。
    selectByMap方法返回值用list集合接收。
     */
    @Test
    public void selectByMap() {
        User user = new User(){{ setUserName("老方");}};
        Map<String, Object> map = new HashMap<String, Object>(){{put("user_name","老方");}};
        List<User> users = userMapper.selectByMap(map);
        System.out.println(users);
    }


    /*
    根据id集合查询
     */
    @Test
    public void selectBatchIds(){
        List<Long> ids = Arrays.asList(1303188042126798850L, 1303187842339516417L);
        List<User> usersReturn = userMapper.selectBatchIds(ids);
        System.out.println(usersReturn);
    }


    /*
    分页查询 ,不加条件
     */
    @Test
    public void selectPage(){
        List<User> users = userMapper.selectPage(new Page<>(1, 2), null);
        System.out.println(users);
    }

    /*
    根据id删除
     */
    @Test
    public void deleteById(){
        userMapper.deleteById(1303188042126798850L);
    }

    /*
    该方法与selectByMap类似，将条件封装在columnMap中，然后调用deleteByMap方法，
    传入columnMap即可，返回值是Integer类型，表示影响的行数
     */
    @Test
    public void deleteByMap(){
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("user_name","老方");
        columnMap.put("id",2);
        userMapper.deleteByMap(columnMap);
    }


    /*
    该方法和selectBatchIds类似，把需要删除的记录的id装进idList，
    然后调用deleteBatchIds，传入idList即可
     */
    @Test
    public void deleteBatchIds(){
        List<Integer> idList = new ArrayList<>();
        idList.add(1);
        idList.add(2);
        userMapper.deleteBatchIds(idList);
    }




    // 构造条件
    /*
    lt：less than 小于
    le：less than or equal to 小于等于
    eq：equal to 等于
    ne：not equal to 不等于
    ge：greater than or equal to 大于等于
    gt：greater than 大于
     */
    /*
    分页条件查询
     */
    @Test
    public void selectPageCondition(){
        Page<User> result = new Page<>(1, 10);
        List<User> users = userMapper.selectPage(result,
                new EntityWrapper<User>()
                        .gt("user_age",13)
                        .le("user_age", 33)
                        .like("user_name","老方"));

        result.setRecords(users);
        System.out.println(users);
    }

    /*
    手写分页
     */
    @Test
    public void selectPageByOwn(){
        Page<User> userPage = new Page<>(1, 3);
        userPage.setRecords(userMapper.selectPageByOwn(userPage));
        System.out.println("aaa");
    }



    @Test
    public void testAndOr(){
        // select * from user where user_name = '老方' and user_age = 15;
       /* List<User> users = userMapper.selectList(
                new EntityWrapper<User>()
                        *//*.and()*//*
                        .eq("user_name", "老方")
                        .and()
                        .eq("user_age",15)
                        .or()
                        .eq("user_email","999@qq.com"));*/

        List<User> users1 = new User().selectList(new EntityWrapper<User>()
                .and()
                .eq("user_name", "老方")
                .and()
                .eq("user_age",15)
                .or()
                .eq("user_email","999@qq.com"));



        Page<User> users2 = new User().selectPage(new Page<User>(1,2),new EntityWrapper<User>()
                .and()
                .eq("user_name", "老方")
                .and()
                .eq("user_age",15)
                .or()
                .eq("user_email","999@qq.com"));


        System.out.println(users2.getRecords());
    }
}



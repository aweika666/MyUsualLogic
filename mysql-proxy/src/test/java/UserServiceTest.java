import com.alibaba.fastjson.JSON;
import com.aweika.MysqlProxyApplication;
import com.aweika.dynamicDataSource.DataSourceSelector;
import com.aweika.dynamicDataSource.DynamicDataSourceEnum;
import com.aweika.entity.User;
import com.aweika.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author: Michael
 * @date: 2020/7/10
 * @description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MysqlProxyApplication.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void SLAVElistUser() {
        List<User> users = userService.SLAVElistUser();
        System.out.println(JSON.toJSONString(users));
    }

    @Test
    @Transactional
    public void MASTERlistUser() {
        List<User> users = userService.MASTERlistUser();
        System.out.println(JSON.toJSONString(users));
    }

    @Test
    public void listUser() {
        List<User> users = userService.listUser();
        System.out.println(JSON.toJSONString(users));
    }
}

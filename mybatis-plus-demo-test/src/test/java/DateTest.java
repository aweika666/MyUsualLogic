import com.aweika.MybatisPlusApplication;
import com.aweika.entity.User;
import com.aweika.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author: Aweika_Fang
 * @date: 2020/9/11
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={MybatisPlusApplication.class})// 指定启动类
public class DateTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void dateTest1(){
        /*User user = new User().setId(1303941305107709954L);
        User user1 = user.selectById();
        System.out.println(user1);*/
        List<User> select = userMapper.select();
        System.out.println(select);

    }
}

package test;

import com.aweika.ConditionalOnXXXTest.BeanConfig;
/*import com.com.aweika.ConditionalOnXXXTest.BeanConfig22;*/
import com.aweika.ConditionalOnXXXTest.Computer;
import com.aweika.SpringAopTestApplication;
import com.aweika.common.response.WebResponse;
import com.aweika.service.impl.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * @author: Michael
 * @date: 2020/2/5
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={SpringAopTestApplication.class})// 指定启动类
public class ApplicationTests {
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    BeanConfig beanConfig;
    /*@Autowired
    BeanConfig22 beanConfig22;*/
    @Autowired
    TestService testService;


    @Test
    public void testBean(){
        //System.out.println(beanConfig.computer3());
        System.out.println(applicationContext.getBean(Computer.class));
        Map<String, Computer> beans = applicationContext.getBeansOfType(Computer.class);
        System.out.println(beans);
    }

    @Test
    public void testBeanClass(){
        Map<String, Computer> beans = applicationContext.getBeansOfType(Computer.class);
        System.out.println(beans);
        System.out.println(applicationContext.getBean("notebookPC"));
      //  System.out.println(beanConfig.computer1());
     //   System.out.println(beanConfig22.computer3());
       // applicationContext.getBean("notebookPC");
    }
}

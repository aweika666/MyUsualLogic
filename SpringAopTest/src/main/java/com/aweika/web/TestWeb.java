package com.aweika.web;


import com.ConfigurationPropertiesTest.MytestBean;
import com.aweika.ConditionalOnXXXTest.BeanConfig;
import com.aweika.ConditionalOnXXXTest.Computer;
import com.aweika.common.response.WebResponse;
import com.aweika.service.impl.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Michael
 * @date: 2020/2/2
 * @description:
 */
@RequestMapping
@RestController
public class TestWeb {

    public TestWeb() {
        System.out.println("TestWeb实例化");
    }

    @Autowired
    private TestService testService;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private BeanConfig beanConfig;

    /*@Autowired
    private Computer computer;*/

    /*@Autowired
    private Computer computer;*/

   /*@GetMapping("testClassload")
   public Object test(Integer id){
       testService.deleteById(id);
       return WebResponse.resSuccess("成功", null);
   }

   @GetMapping("testAop")
   public Object testAop(){
       LogAdvice bean = applicationContext.getBean(LogAdvice.class);
       Integer a = testService.testAop();
       return WebResponse.resSuccess("成功", a);
   }*/

   @GetMapping("mytestBean")
   public Object mytestBean(){
       MytestBean bean = applicationContext.getBean(MytestBean.class);
       return WebResponse.resSuccess("成功", bean);
   }
}

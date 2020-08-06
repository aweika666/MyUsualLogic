package com.aweika.web;

import com.aweika.common.response.WebResponse;
import com.aweika.service.impl.TestService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TestService testService;

   @GetMapping("testClassload")
   public Object test(Integer id){
       testService.deleteById(id);
       return WebResponse.resSuccess("成功", null);
   }
}

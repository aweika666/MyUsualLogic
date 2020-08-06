package com.demo.starter.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Michael
 * @Date: 2019/11/10 5:15 PM
 * @Description: 对服务所有Controller的异常处理
 *
 * DefaultHandlerExceptionResolver
 */
@RestControllerAdvice
@ConditionalOnProperty(prefix = "demo.enabled", name = "excEnabled", havingValue = "true" ,matchIfMissing = false)
public class ServiceExceptionHandler {
    private  Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     *  全局异常处理:处理所有不可知的运行时异常
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleRuntimeException(RuntimeException e){
        log.error(e.getMessage(),e);
        LinkedHashMap map = new LinkedHashMap();
        map.put("cide",9999);
        map.put("msg",e.getMessage());
        /*map.put("data",null);*/
        return map;
    }

}

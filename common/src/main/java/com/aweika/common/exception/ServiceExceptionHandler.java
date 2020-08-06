package com.aweika.common.exception;

import com.aweika.common.response.WebResponse;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
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
public class ServiceExceptionHandler {
    private  Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     *  全局异常处理:处理所有不可知的运行时异常
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public WebResponse handleRuntimeException(RuntimeException e){
        log.error(e.getMessage(),e);
        return WebResponse.resException(e.getMessage(),null);
    }

    /**
     *  接口请求方式不对
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public WebResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        log.error(e.getMessage(),e);
        return WebResponse.resException(e.getMessage(),null);
    }


    /**
     *  @RequestParam :parameter 'id(入参的参数)' is not present(呈现出现)
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public WebResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException e){
        log.error(e.getMessage(),e);
        return WebResponse.resException(e.getMessage(),null);
    }

    /**
     * 方法参数校验:入参类型转换异常
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public WebResponse handleMethodArgumentNotValidException(MethodArgumentTypeMismatchException e) {
        log.error(e.getMessage(),e);
        return WebResponse.resException(e.getMessage(),null);
    }


    /*
    此异常处理器适用于: 校验非实体类的独立入参,需在controller上加@Validated注解
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public WebResponse handleConstraintViolationExceptionHandler(ConstraintViolationException e) {
        List<HashMap> message = e.getConstraintViolations().stream().map(s -> {
            HashMap map = new HashMap();
            map.put("field", ((PathImpl)s.getPropertyPath()).getLeafNode().getName());
            map.put("message", s.getMessage());
            return map;
        }).collect(Collectors.toList());
        log.error(e.getMessage(),e);
        //取一条错误
        String oneMessage = message.get(0).get("field") + "" + message.get(0).get("message");
        return WebResponse.resException(oneMessage,null);
    }


    /*
   此异常处理器适用于: 使用@Valid 验证路径中请求实体校验失败后抛出的异常
    */

    @ExceptionHandler(BindException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public WebResponse handleBindExceptionHandler(BindException e) {
        List<HashMap> message = e.getAllErrors().stream().map(s -> {
            HashMap map = new HashMap();
            map.put("field",((FieldError)s).getField());
            map.put("message", s.getDefaultMessage());
            return map;
        }).collect(Collectors.toList());
        //String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        log.error(e.getMessage(),e);
        //取一条错误
        String oneMessage = message.get(0).get("field") + "" + message.get(0).get("message");
        return WebResponse.resException(oneMessage,null);
    }

    /*
    此异常处理器适用于: 处理请求参数格式错误 @RequestBody上@Valid或者@validate失败后抛出的异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public WebResponse handleMethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<HashMap> message = e.getBindingResult().getAllErrors().stream().map(s -> {
            HashMap map = new HashMap();
            map.put("field", ((FieldError)s).getField());
            map.put("message", s.getDefaultMessage());
            return map;
        }).collect(Collectors.toList());
        log.error(e.getMessage(),e);
        //取一条错误
        String oneMessage = message.get(0).get("field") + "" + message.get(0).get("message");
        return WebResponse.resException(oneMessage,null);
    }

}

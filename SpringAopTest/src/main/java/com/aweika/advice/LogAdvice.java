package com.aweika.advice;


import com.ConfigurationPropertiesTest.MytestBean;
import com.aweika.annotation.LogAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 统一日志处理
 *
 */
@Aspect
/*@EnableConfigurationProperties(MytestBean.class)*/
/*@Import({TestServiceImpl.class})*/
public class LogAdvice {

	public LogAdvice() {
		System.out.println("LogAdvice");
	}

	@Around(value = "@annotation(com.aweika.annotation.LogAnnotation)")
	public Object logSave(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		String module = null;
		LogAnnotation annotation = methodSignature.getMethod().getAnnotation(LogAnnotation.class);
		LogAnnotation annotation2 = methodSignature.getMethod().getDeclaredAnnotation(LogAnnotation.class);System.out.println(annotation.module());
		System.out.println("aaaaaaa---aaaaaaa");
		Object flag = joinPoint.proceed();
		System.out.println("bbbbbbb---bbbbbbb");
		return flag;
	}

	/*@Before(value = "@annotation(com.com.aweika.annotation.LogAnnotation)")
	public Object begin(JoinPoint joinPoint) throws Throwable {
		Object proceed = ((ProceedingJoinPoint) joinPoint).proceed();
		String name = joinPoint.getSignature().getName();
		System.out.println("==@Before== lingyejun blog logger : begin");
		return "11";
	}*/
	
	/*public String getParameterLog(ProceedingJoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		Annotation[][] parameterAnnotations = methodSignature.getMethod().getParameterAnnotations();
		for (Annotation[] parameterAnnotation: parameterAnnotations) {
			int paramIndex= ArrayUtils.indexOf(parameterAnnotations, parameterAnnotation);
			for (Annotation annotation: parameterAnnotation) {
				if (annotation instanceof ApiParameter){
					ApiParameter apiParameter = (ApiParameter)annotation;
					Object obj = args[paramIndex];
					try {
						Method method = obj.getClass().getDeclaredMethod("getLogString");
						((RoleDto)obj).getLogString();
						obj = method.invoke(obj);
					} catch (Exception e) {
						obj = args[paramIndex];
					}
					parameterMap.put(apiParameter.name(), obj);
				}
			}
		}
		return JSON.toJSONString(parameterMap);
	}*/
	
}

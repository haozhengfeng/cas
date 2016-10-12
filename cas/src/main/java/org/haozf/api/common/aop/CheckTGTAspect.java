package org.haozf.api.common.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.haozf.common.annotation.WithOutlogin;
import org.haozf.common.base.BaseController;
import org.haozf.common.enums.ApiStatus;
import org.haozf.common.model.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 通过aop方式判断用户是否登录
 * BaseController中，initModel方法会通过json返回当前登录状态
 * 执行controller 方法之前  判断TGT， 如果没有TGT返回json
 * withoutlogin结尾的方法不需要验证TGT
 * 
 * 
 * 用户登录状态的验证已经已修改为通过interceptor拦截器实现
 * @see org.haozf.api.common.interceptor.UserInterceptor
 * 
 * @author haozhengfeng
 *
 */
//@Component
@Aspect
@Deprecated
public class CheckTGTAspect {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 定义一个切入点
	 */
	@Pointcut("execution(* org.haozf.api.user..*Controller.*User*(..))")
	private void anyMethod() {}
	
	@Around("anyMethod()")  
    public Object doBasicProfiling(ProceedingJoinPoint point) throws Throwable{
//		String methodName = point.getSignature().getName();
		//获取当前方法的Method对象
		Method method = ((MethodSignature) point.getSignature()).getMethod();  
		
		Object obj = point.getTarget();
		if(obj instanceof BaseController){
			Json json = ((BaseController)obj).json;
			
			//判断这个方法上是否有WithOutlogin这个注解  
            if (!method.isAnnotationPresent(WithOutlogin.class)) {
            	if(logger.isDebugEnabled()){
					logger.debug("json:"+json);
				}
				if(json.getStatus()==ApiStatus.fail){
					if(logger.isDebugEnabled()){
						logger.debug("无权访问api接口");
					}
					return json;
				}
            } 
            
			
			json.setStatus(ApiStatus.success);
			json.setMessage("");
			
		}
		Object object = point.proceed();
        return object;  
    }  

}

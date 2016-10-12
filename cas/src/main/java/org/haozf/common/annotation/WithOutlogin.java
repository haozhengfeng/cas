package org.haozf.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 通过注解方式控制方法是否可以在未登录时调用
 * 2016年6月29日
 * @author haozhengfeng
 * 
 */
@Documented  
@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.METHOD})  
public @interface WithOutlogin {

}

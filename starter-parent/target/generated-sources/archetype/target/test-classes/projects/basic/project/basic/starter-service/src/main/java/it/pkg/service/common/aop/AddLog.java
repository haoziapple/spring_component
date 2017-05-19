package it.pkg.service.common.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @className:it.pkg.service.common.aop.AddLog
 * @description:系统日志注解
 * @version:v0.1-SNAPSHOT
 * @date:2017年5月18日 上午10:36:18
 * @author:WangHao
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AddLog
{
	String value() default "";
}

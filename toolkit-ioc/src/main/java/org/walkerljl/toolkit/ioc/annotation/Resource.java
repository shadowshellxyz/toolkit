/*
 * Copyright (c) 2010-2015 www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.toolkit.ioc.annotation;

import java.lang.annotation.Retention;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Resource 
 *
 * @author lijunlin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Resource {
	
	/**
	 * Bean名称
	 * @return
	 */
	String name() default "";
	
	/***
	 * 实现类
	 * @return
	 */
	Class<?> impl() default Object.class;
}

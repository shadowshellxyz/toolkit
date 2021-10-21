/*
 * Copyright (c) 2010-2021 www.shadowdefender.net All Rights Reserved.
 */
package net.shadowdefender.toolkit.ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Controller 
 *
 * @author shadow
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Controller {
	
}

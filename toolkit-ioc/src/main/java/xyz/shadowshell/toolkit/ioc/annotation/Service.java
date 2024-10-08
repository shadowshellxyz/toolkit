/*
 * Copyright (c) 2010-2021 www.shadowdefender.net All Rights Reserved.
 */
package xyz.shadowshell.toolkit.ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Service
 *
 * @author shadowwalkerxyz@qq.com<wwww.shadowshell.xyz>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Service {

}

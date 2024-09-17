/*
 * Copyright (c) 2010-2021 www.shadowdefender.net All Rights Reserved.
 */
package xyz.shadowshell.toolkit.ioc.scanner;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * 类扫描器
 *
 * @author shadowwalkerxyz@qq.com<wwww.shadowshell.xyz>
 */
interface ClassScanner {

    /**
     * 获取指定包名中的所有类
     *
     * @param packageName 父类
     * @return 满足条件的类列表
     */
    List<Class<?>> getClassList(String packageName);

    /**
     * 获取指定包名中指定注解的相关类
     *
     * @param packageName     包名
     * @param annotationClass 父类
     * @return 满足条件的类列表
     */
    List<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass);

    /**
     * 获取指定包名中指定父类或接口的相关类
     *
     * @param packageName 包名
     * @param superClass  父类
     * @return 满足条件的类列表
     */
    List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass);
}
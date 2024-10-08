package xyz.shadowshell.toolkit.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义切面顺序
 *
 * @author shadowwalkerxyz@qq.com<wwww.shadowshell.xyz>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AspectOrder {

    /**
     * 顺序值
     *
     * @return
     */
    int value();
}

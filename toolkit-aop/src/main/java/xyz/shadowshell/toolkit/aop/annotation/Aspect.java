package xyz.shadowshell.toolkit.aop.annotation;

import java.lang.annotation.*;

/**
 * Aspect
 *
 * @author shadowwalkerxyz@qq.com<wwww.shadowshell.xyz>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    /**
     * 包
     *
     * @return
     */
    String packageName() default "";

    /**
     * 类名
     *
     * @return
     */
    String className() default "";

    /**
     * 注解
     *
     * @return
     */
    Class<? extends Annotation> annotation() default Aspect.class;
}
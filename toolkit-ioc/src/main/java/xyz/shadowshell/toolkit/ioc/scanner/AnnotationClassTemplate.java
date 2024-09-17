package xyz.shadowshell.toolkit.ioc.scanner;

import java.lang.annotation.Annotation;

/**
 * 获取注解的模板类
 *
 * @author shadowwalkerxyz@qq.com<wwww.shadowshell.xyz>
 */
abstract class AnnotationClassTemplate extends ClassTemplate {

    protected final Class<? extends Annotation> annotationClass;

    protected AnnotationClassTemplate(String packageName, Class<? extends Annotation> annotationClass) {
        super(packageName);
        this.annotationClass = annotationClass;
    }
}

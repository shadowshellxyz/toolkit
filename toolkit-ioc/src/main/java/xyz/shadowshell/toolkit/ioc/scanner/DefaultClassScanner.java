package xyz.shadowshell.toolkit.ioc.scanner;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * 默认的类扫描器
 *
 * @author shadowwalkerxyz@qq.com<wwww.shadowshell.xyz>
 */
class DefaultClassScanner implements ClassScanner {

    @Override
    public List<Class<?>> getClassList(String packageName) {
        return new ClassTemplate(packageName) {
            @Override
            public boolean canAddClass(Class<?> clazz) {
                String className = clazz.getName();
                String pkgName = className.substring(0, className.lastIndexOf("."));
                return pkgName.startsWith(packageName);
            }
        }.getClassList();
    }

    @Override
    public List<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass) {
        return new AnnotationClassTemplate(packageName, annotationClass) {
            @Override
            public boolean canAddClass(Class<?> clazz) {
                return clazz.isAnnotationPresent(annotationClass);
            }
        }.getClassList();
    }

    @Override
    public List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass) {
        return new SupperClassTemplate(packageName, superClass) {
            @Override
            public boolean canAddClass(Class<?> clazz) {
                return superClass.isAssignableFrom(clazz) && !superClass.equals(clazz);
            }
        }.getClassList();
    }
}
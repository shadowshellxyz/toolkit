package xyz.shadowshell.toolkit.ioc.scanner;

/**
 * 获取子类的模板类
 *
 * @author shadow
 */
abstract class SupperClassTemplate extends ClassTemplate {

    protected final Class<?> superClass;

    protected SupperClassTemplate(String packageName, Class<?> superClass) {
        super(packageName);
        this.superClass = superClass;
    }
}

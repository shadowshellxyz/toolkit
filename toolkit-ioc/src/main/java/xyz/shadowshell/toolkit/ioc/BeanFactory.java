package xyz.shadowshell.toolkit.ioc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xyz.shadowshell.toolkit.ioc.annotation.Component;
import xyz.shadowshell.toolkit.ioc.annotation.Controller;
import xyz.shadowshell.toolkit.ioc.annotation.Repository;
import xyz.shadowshell.toolkit.ioc.annotation.Service;
import xyz.shadowshell.toolkit.lang.StringUtils;
import xyz.shadowshell.toolkit.logging.Logger;
import xyz.shadowshell.toolkit.logging.LoggerFactory;


/**
 * 初始化Bean
 *
 * @author shadowwalkerxyz@qq.com<wwww.shadowshell.xyz>
 */
class BeanFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanFactory.class);
    /**
     * 包名
     */
    private String packageName;
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();

    /**
     * 构造函数，必须制定获取Bean的包名
     *
     * @param packageName
     */
    public BeanFactory(String packageName) {
        this.packageName = packageName;
    }

    /**
     * 初始化，获取标注了制定注解的Bean
     */
    public void init() {
        if (StringUtils.isEmpty(packageName)) {
            LOGGER.warn("Package name is null or empty!");
            return;
        }
        try {
            // 获取应用包路径下所有的类
            List<Class<?>> classList = ClassScannerUtil.getClassList(this.packageName);
            for (Class<?> clazz : classList) {
                if (clazz.isAnnotationPresent(Component.class) || clazz.isAnnotationPresent(Repository.class)
                        || clazz.isAnnotationPresent(Service.class) || clazz.isAnnotationPresent(Controller.class)) {

                    Object beanInstance = clazz.newInstance();
                    BEAN_MAP.put(clazz, beanInstance);
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug(String.format("Register class : ", clazz.getName()));
                    }
                }
            }
        } catch (Throwable e) {
            String errMsg = String.format("Init bean error, package name : ", packageName);
            LOGGER.error(errMsg);
            throw new IocException(errMsg, e);
        }
    }

    /**
     * 获取BeanMap
     *
     * @return
     */
    public Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    /**
     * 获取Bean实例
     *
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> clazz) {
        return clazz == null ? null : (T) BEAN_MAP.get(clazz);
    }

    /**
     * 设置Bean实例
     *
     * @param clazz
     * @param object
     */
    public void setBean(Class<?> clazz, Object object) {
        BEAN_MAP.put(clazz, object);
    }
}
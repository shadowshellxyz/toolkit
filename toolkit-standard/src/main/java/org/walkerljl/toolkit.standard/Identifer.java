package org.walkerljl.toolkit.standard;

/**
 * 描述一个具有ID、名称、分组标识符的对象
 *
 * @author: xingxun
 * @Date: 2015/01/01
 */
public interface Identifer {

    /**
     * 获取ID
     *
     * @return
     */
    String getId();

    /**
     * 获取名称
     *
     * @return
     */
    String getName();

    /**
     * 获取分组
     *
     * @return
     */
    String getGroup();
}

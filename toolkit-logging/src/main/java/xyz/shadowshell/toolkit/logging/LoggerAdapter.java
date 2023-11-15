/*
 * Copyright (c) 2013 lijunlin All Rights Reserved.
 * 本软件源代码版权归作者所有,未经许可不得任意复制与传播.
 */
package xyz.shadowshell.toolkit.logging;

import java.io.File;

/**
 * 日志输出对象适配器
 *
 * @author shadow
 * @since 2014-2-12
 */
public interface LoggerAdapter {

    /**
     * 获取日志输出对象
     *
     * @param clazz
     * @return
     */
    Logger getLogger(Class<?> clazz);

    /**
     * 获取日志输出对象
     *
     * @param key
     * @return
     */
    Logger getLogger(String key);

    /**
     * 获取当前日志等级
     *
     * @return 当前日志等级
     */
    Level getLevel();

    /**
     * 设置输出等级
     *
     * @param level 输出等级
     */
    void setLevel(Level level);

    /**
     * 获取当前日志文件
     *
     * @return 当前日志文件
     */
    File getFile();

    /**
     * 设置输出日志文件
     *
     * @param file 输出日志文件
     */
    void setFile(File file);
}
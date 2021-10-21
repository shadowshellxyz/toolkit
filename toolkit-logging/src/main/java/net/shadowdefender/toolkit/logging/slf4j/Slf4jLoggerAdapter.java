/*
 * Copyright (c) 2013 lijunlin All Rights Reserved.
 * 本软件源代码版权归作者所有,未经许可不得任意复制与传播.
 */
package net.shadowdefender.toolkit.logging.slf4j;

import java.io.File;

import net.shadowdefender.toolkit.logging.Level;
import net.shadowdefender.toolkit.logging.Logger;
import net.shadowdefender.toolkit.logging.LoggerAdapter;

/**
 * Slf4jLoggerAdapter
 *
 * @author shadow
 * @since 2014-2-13
 */
public class Slf4jLoggerAdapter implements LoggerAdapter {

    private Level level;

    private File file;

    public Logger getLogger(String key) {
        return new Slf4jLogger(org.slf4j.LoggerFactory.getLogger(key));
    }

    public Logger getLogger(Class<?> key) {
        return new Slf4jLogger(org.slf4j.LoggerFactory.getLogger(key));
    }

    @Override
    public Level getLevel() {
        return level;
    }

    @Override
    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public void setFile(File file) {
        this.file = file;
    }
}
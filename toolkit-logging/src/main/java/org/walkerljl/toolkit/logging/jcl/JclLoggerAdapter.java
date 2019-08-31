/*
 * Copyright (c) 2013 lijunlin All Rights Reserved.
 * 本软件源代码版权归作者所有,未经许可不得任意复制与传播.
 */
package org.walkerljl.toolkit.logging.jcl;

import java.io.File;

import org.apache.commons.logging.LogFactory;
import org.walkerljl.toolkit.logging.Level;
import org.walkerljl.toolkit.logging.Logger;
import org.walkerljl.toolkit.logging.LoggerAdapter;

/**
 * JclLoggerAdapter
 *
 * @author lijunlin
 * @since 2014-2-12
 */
public class JclLoggerAdapter implements LoggerAdapter {

    private Level level;

    private File file;

    @Override
    public Logger getLogger(Class<?> clazz) {
        return new JclLogger(LogFactory.getLog(clazz));
    }

    @Override
    public Logger getLogger(String key) {
        return new JclLogger(LogFactory.getLog(key));
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
/*
 * Copyright (c) 2013 lijunlin All Rights Reserved.
 * 本软件源代码版权归作者所有,未经许可不得任意复制与传播.
 */
package org.walkerljl.toolkit.logging.jdk;

import java.util.logging.Level;

import org.walkerljl.toolkit.logging.Logger;

/**
 * JdkLogger
 *
 * @author lijunlin
 * @since 2014-2-12
 */
public class JdkLogger implements Logger {

    private final java.util.logging.Logger logger;

    public JdkLogger(java.util.logging.Logger logger) {
        this.logger = logger;
    }

    @Override
    public boolean isTraceEnabled() {
        return logger.isLoggable(Level.FINER);
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isLoggable(Level.FINE);
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isLoggable(Level.INFO);
    }

    @Override
    public boolean isWarnEnabled() {
        return logger.isLoggable(Level.WARNING);
    }

    @Override
    public boolean isErrorEnabled() {
        return logger.isLoggable(Level.SEVERE);
    }

    @Override
    public void trace(Object message) {
        logger.log(Level.FINER, String.valueOf(message));
    }

    @Override
    public void trace(Object message, Throwable e) {
        logger.log(Level.FINER, String.valueOf(message), e);
    }

    @Override
    public void debug(Object message) {
        logger.log(Level.FINE, String.valueOf(message));
    }

    @Override
    public void debug(Object message, Throwable e) {
        logger.log(Level.FINE, String.valueOf(message), e);
    }

    @Override
    public void info(Object message) {
        logger.log(Level.INFO, String.valueOf(message));
    }

    @Override
    public void info(Object message, Throwable e) {
        logger.log(Level.INFO, String.valueOf(message), e);
    }

    @Override
    public void warn(Object message) {
        logger.log(Level.WARNING, String.valueOf(message));
    }

    @Override
    public void warn(Object message, Throwable e) {
        logger.log(Level.WARNING, String.valueOf(message), e);
    }

    @Override
    public void error(Object message) {
        logger.log(Level.SEVERE, String.valueOf(message));
    }

    @Override
    public void error(Object message, Throwable e) {
        logger.log(Level.SEVERE, String.valueOf(message), e);
    }
}
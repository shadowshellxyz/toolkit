/*
 * Copyright (c) 2013 lijunlin All Rights Reserved.
 * 本软件源代码版权归作者所有,未经许可不得任意复制与传播.
 */
package net.shadowdefender.toolkit.logging.slf4j;

import java.io.Serializable;

import net.shadowdefender.toolkit.logging.Logger;

/**
 * Slf4jLogger
 *
 * @author shadow
 * @since 2014-2-13
 */
public class Slf4jLogger implements Logger, Serializable {

    private static final long serialVersionUID = 1L;

    private final org.slf4j.Logger logger;

    public Slf4jLogger(org.slf4j.Logger logger) {
        this.logger = logger;
    }

    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    @Override
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    @Override
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    @Override
    public void trace(Object message) {
        logger.trace(String.valueOf(message));
    }

    @Override
    public void trace(Object message, Throwable e) {
        logger.trace(String.valueOf(message), e);
    }

    @Override
    public void debug(Object message) {
        logger.debug(String.valueOf(message));
    }

    @Override
    public void debug(Object message, Throwable e) {
        logger.debug(String.valueOf(message), e);
    }

    @Override
    public void info(Object message) {
        logger.info(String.valueOf(message));
    }

    @Override
    public void info(Object message, Throwable e) {
        logger.info(String.valueOf(message), e);
    }

    @Override
    public void warn(Object message) {
        logger.warn(String.valueOf(message));
    }

    @Override
    public void warn(Object message, Throwable e) {
        logger.warn(String.valueOf(message), e);
    }

    @Override
    public void error(Object message) {
        logger.error(String.valueOf(message));
    }

    @Override
    public void error(Object message, Throwable e) {
        logger.error(String.valueOf(message), e);
    }
}
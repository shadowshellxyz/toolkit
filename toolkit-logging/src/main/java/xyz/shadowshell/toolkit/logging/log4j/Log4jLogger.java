/*
 * Copyright (c) 2013 lijunlin All Rights Reserved.
 * 本软件源代码版权归作者所有,未经许可不得任意复制与传播.
 */
package xyz.shadowshell.toolkit.logging.log4j;

import xyz.shadowshell.toolkit.logging.support.FailsafeLogger;
import org.apache.log4j.Level;
import xyz.shadowshell.toolkit.logging.Logger;

/**
 * Log4jLogger
 *
 * @author shadow
 * @since 2014-2-12
 */
public class Log4jLogger implements Logger {

    private static final String FQCN = FailsafeLogger.class.getName();

    private final org.apache.log4j.Logger logger;

    public Log4jLogger(org.apache.log4j.Logger logger) {
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
        return logger.isEnabledFor(Level.WARN);
    }

    @Override
    public boolean isErrorEnabled() {
        return logger.isEnabledFor(Level.ERROR);
    }

    @Override
    public void trace(Object message) {
        logger.log(FQCN, Level.TRACE, message, null);
    }

    @Override
    public void trace(Object message, Throwable e) {
        logger.log(FQCN, Level.TRACE, message, e);
    }

    @Override
    public void debug(Object message) {
        logger.log(FQCN, Level.DEBUG, message, null);
    }

    @Override
    public void debug(Object message, Throwable e) {
        logger.log(FQCN, Level.DEBUG, message, e);
    }

    @Override
    public void info(Object message) {
        logger.log(FQCN, Level.INFO, message, null);
    }

    @Override
    public void info(Object message, Throwable e) {
        logger.log(FQCN, Level.INFO, message, e);
    }

    @Override
    public void warn(Object message) {
        logger.log(FQCN, Level.WARN, message, null);
    }

    @Override
    public void warn(Object message, Throwable e) {
        logger.log(FQCN, Level.WARN, message, e);
    }

    @Override
    public void error(Object message) {
        logger.log(FQCN, Level.ERROR, message, null);
    }

    @Override
    public void error(Object message, Throwable e) {
        logger.log(FQCN, Level.ERROR, message, e);
    }
}
/*
 * Copyright (c) 2013 lijunlin All Rights Reserved.
 * 本软件源代码版权归作者所有,未经许可不得任意复制与传播.
 */
package net.shadowdefender.toolkit.logging.jcl;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import net.shadowdefender.toolkit.logging.Logger;

/**
 * 适配CommonsLogging，依赖于commons-logging.jar
 * <br/>
 * 有关CommonsLogging详细信息请参阅：<a target="_blank" href="http://www.apache.org/">http://www.apache.org/</a>
 *
 * @author shadow
 * @since 2014-2-12
 */
public class JclLogger implements Logger, Serializable {

    private static final long serialVersionUID = 1L;

    private final Log logger;

    public JclLogger(Log logger) {
        this.logger = logger;
    }

    @Override
    public boolean isTraceEnabled() {
        return this.logger.isTraceEnabled();
    }

    @Override
    public boolean isDebugEnabled() {
        return this.logger.isDebugEnabled();
    }

    @Override
    public boolean isInfoEnabled() {
        return this.logger.isInfoEnabled();
    }

    @Override
    public boolean isWarnEnabled() {
        return this.logger.isWarnEnabled();
    }

    @Override
    public boolean isErrorEnabled() {
        return this.logger.isErrorEnabled();
    }

    @Override
    public void trace(Object message) {
        this.logger.trace(message);
    }

    @Override
    public void trace(Object message, Throwable e) {
        this.logger.trace(message, e);
    }

    @Override
    public void debug(Object message) {
        this.logger.debug(message);
    }

    @Override
    public void debug(Object message, Throwable e) {
        this.logger.debug(message, e);
    }

    @Override
    public void info(Object message) {
        this.logger.info(message);
    }

    @Override
    public void info(Object message, Throwable e) {
        this.logger.info(message, e);
    }

    @Override
    public void warn(Object message) {
        this.logger.warn(message);
    }

    @Override
    public void warn(Object message, Throwable e) {
        this.logger.warn(message, e);
    }

    @Override
    public void error(Object message) {
        this.logger.error(message);
    }

    @Override
    public void error(Object message, Throwable e) {
        this.logger.error(message, e);
    }
}
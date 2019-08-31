/*
 * Copyright (c) 2013 lijunlin All Rights Reserved.
 * 本软件源代码版权归作者所有,未经许可不得任意复制与传播.
 */
package org.walkerljl.toolkit.logging.support;

import org.walkerljl.toolkit.logging.Logger;

/**
 * 日志类
 *
 * @author lijunlin
 * @since 2014-2-12
 */
public class FailsafeLogger implements Logger {

    private Logger logger;

    public FailsafeLogger(Logger logger) {
        this.logger = logger;
    }

    public Logger getLogger() {
        return this.logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public boolean isTraceEnabled() {
        try {
            return this.logger.isTraceEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean isDebugEnabled() {
        try {
            return this.logger.isDebugEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean isInfoEnabled() {
        try {
            return this.logger.isInfoEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean isWarnEnabled() {
        try {
            return this.logger.isWarnEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean isErrorEnabled() {
        try {
            return this.logger.isErrorEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void trace(Object message) {
        try {
            this.logger.trace(appendContextMessage(message));
        } catch (Throwable e) {
        }
    }

    @Override
    public void trace(Object message, Throwable e) {
        try {
            this.logger.trace(appendContextMessage(message), e);
        } catch (Throwable ex) {
        }
    }

    @Override
    public void debug(Object message) {
        try {
            this.logger.debug(appendContextMessage(message));
        } catch (Exception e) {
        }
    }

    @Override
    public void debug(Object message, Throwable e) {
        try {
            this.logger.debug(appendContextMessage(message), e);
        } catch (Exception ex) {
        }
    }

    @Override
    public void info(Object message) {
        try {
            this.logger.info(appendContextMessage(message));
        } catch (Exception e) {
        }
    }

    @Override
    public void info(Object message, Throwable e) {
        try {
            this.logger.info(appendContextMessage(message), e);
        } catch (Exception ex) {
        }
    }

    @Override
    public void warn(Object message) {
        try {
            this.logger.warn(appendContextMessage(message));
        } catch (Exception e) {
        }
    }

    @Override
    public void warn(Object message, Throwable e) {
        try {
            this.logger.warn(appendContextMessage(message), e);
        } catch (Exception ex) {
        }
    }

    @Override
    public void error(Object message) {
        try {
            this.logger.error(appendContextMessage(message));
        } catch (Exception e) {
        }
    }

    @Override
    public void error(Object message, Throwable e) {
        try {
            this.logger.error(appendContextMessage(message), e);
        } catch (Exception ex) {
        }
    }

    private String appendContextMessage(Object message) {
        //		StringBuilder sb = new StringBuilder();
        //			sb.append(" [JARVIS] ").append(msg).append(", jarvis version: ").append(Version.getVersion());
        //			sb.append(", current host: ").append(NetUtils.getLogHost());
        //		return sb.toString();
        return message.toString();
    }
}
package xyz.shadowshell.toolkit.template.log.defaults;

import xyz.shadowshell.toolkit.template.log.Logger;

/**
 * DefaultLogger
 *
 * @author shadow
 */
public class DefaultLogger implements Logger {

    private final xyz.shadowshell.toolkit.logging.Logger logger;

    public DefaultLogger(xyz.shadowshell.toolkit.logging.Logger logger) {
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
        logger.trace(message);
    }

    @Override
    public void trace(Object message, Throwable e) {
        logger.trace(message, e);
    }

    @Override
    public void debug(Object message) {
        logger.debug(message);
    }

    @Override
    public void debug(Object message, Throwable e) {
        logger.debug(message, e);
    }

    @Override
    public void info(Object message) {
        logger.info(message);
    }

    @Override
    public void info(Object message, Throwable e) {
        logger.info(message, e);
    }

    @Override
    public void warn(Object message) {
        logger.warn(message);
    }

    @Override
    public void warn(Object message, Throwable e) {
        logger.warn(message, e);
    }

    @Override
    public void error(Object message) {
        logger.error(message);
    }

    @Override
    public void error(Object message, Throwable e) {
        logger.error(message, e);
    }
}
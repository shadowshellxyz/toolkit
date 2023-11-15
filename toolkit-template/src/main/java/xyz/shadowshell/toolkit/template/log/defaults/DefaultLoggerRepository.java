package xyz.shadowshell.toolkit.template.log.defaults;

import xyz.shadowshell.toolkit.logging.LoggerFactory;
import xyz.shadowshell.toolkit.template.log.Logger;
import xyz.shadowshell.toolkit.template.log.LoggerRepository;

/**
 * @author shadow
 */
public class DefaultLoggerRepository implements LoggerRepository {

    @Override
    public Logger getLogger(Class<?> clazz) {
        return new DefaultLogger(LoggerFactory.getLogger(clazz));
    }

    @Override
    public Logger getLogger(String name) {
        return new DefaultLogger(LoggerFactory.getLogger(name));
    }
}
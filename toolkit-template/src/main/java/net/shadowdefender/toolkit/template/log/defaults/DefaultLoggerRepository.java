package net.shadowdefender.toolkit.template.log.defaults;

import net.shadowdefender.toolkit.logging.LoggerFactory;
import net.shadowdefender.toolkit.template.log.Logger;
import net.shadowdefender.toolkit.template.log.LoggerRepository;

/**
 *
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
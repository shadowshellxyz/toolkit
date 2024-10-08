package xyz.shadowshell.toolkit.template.log;

/**
 * LoggerFactory
 *
 * @author shadow
 */
public class LoggerFactory {

    private static LoggerRepository loggerRepository;

    public static Logger getLogger(Class<?> clazz) {
        return loggerRepository.getLogger(clazz);
    }

    public static Logger getLogger(String name) {
        return loggerRepository.getLogger(name);
    }

    public static void bindLoggerRepository(LoggerRepository loggerRepository) {
        LoggerFactory.loggerRepository = loggerRepository;
    }
}
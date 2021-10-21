package net.shadowdefender.toolkit.template;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import net.shadowdefender.toolkit.template.log.Logger;
import net.shadowdefender.toolkit.template.log.LoggerFactory;
import net.shadowdefender.toolkit.template.log.defaults.DefaultLoggerRepository;

/**
 * @author shadow
 */
public class BaseUnitTest {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    static {
        LoggerFactory.bindLoggerRepository(new DefaultLoggerRepository());
    }

    @BeforeMethod
    public void before() {
        LOGGER.debug("Initialized some resoruces.");
    }

    @AfterMethod
    public void after() {
        LOGGER.debug("Released some resources.");
    }
}
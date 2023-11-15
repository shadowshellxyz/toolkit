package xyz.shadowshell.toolkit.standard;

import org.junit.After;
import org.junit.Before;
import xyz.shadowshell.toolkit.logging.Logger;
import xyz.shadowshell.toolkit.logging.LoggerFactory;

/**
 * 单元测试基类
 *
 * @author shadow
 */
public class BaseUnitTest {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Before
    public void before() {
        LOGGER.debug("Initialized some resoruces.");
    }

    @After
    public void after() {
        LOGGER.debug("Released some resources.");
    }
}
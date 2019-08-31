package org.walkerljl.toolkit.standard;

import org.junit.After;
import org.junit.Before;
import org.walkerljl.toolkit.logging.Logger;
import org.walkerljl.toolkit.logging.LoggerFactory;

/**
 *
 * 单元测试基类
 *
 * @author xingxun
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
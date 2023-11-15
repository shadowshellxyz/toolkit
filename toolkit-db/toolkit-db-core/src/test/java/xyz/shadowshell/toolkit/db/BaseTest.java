package xyz.shadowshell.toolkit.db;

import org.junit.Test;
import net.shadowdefender.toolkit.logging.Logger;
import net.shadowdefender.toolkit.logging.LoggerFactory;

/**
 * BaseTest
 *
 * @author shadow
 */
public class BaseTest {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Test
    public void run() {
        try {
            doTest();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void doTest() {
    }
}

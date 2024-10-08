package xyz.shadowshell.toolkit.standard.resource.abstracts;

import org.junit.Assert;
import org.junit.Test;
import xyz.shadowshell.toolkit.standard.resource.Resource;
import xyz.shadowshell.toolkit.standard.resource.ResourceRepository;
import xyz.shadowshell.toolkit.standard.resource.exception.CannotDestroyResourceException;
import xyz.shadowshell.toolkit.standard.resource.exception.CannotInitResourceException;
import xyz.shadowshell.toolkit.standard.resource.impl.ResourceRepositoryFactory;

/**
 * AbstractResourceTest
 *
 * @author shadow
 * @Date 2016/1/3
 */
public class AbstractResourceTest {

    @Test
    public void test() {

        DefaultResource actual = new DefaultResource();

        Assert.assertEquals("DefaultResource", actual.getId());
        Assert.assertEquals(actual.getId(), actual.getName());
        Assert.assertEquals("Resource", actual.getGroup());

        String expectedInstanceId = actual.toString();
        Assert.assertEquals(actual.getInstanceId(), expectedInstanceId);

        ResourceRepository objectRepository = ResourceRepositoryFactory.getDefaultRepository();
        String key = objectRepository.buildKey(actual.getGroup(), actual.getId());

        Assert.assertNull(objectRepository.lookup(key));
        Assert.assertFalse(actual.isInited());
        actual.init();
        Assert.assertTrue(actual.isInited());
        Assert.assertEquals(actual, objectRepository.lookup(key));
        actual.destroy();
        Assert.assertFalse(actual.isInited());
        Assert.assertNull(objectRepository.lookup(key));
    }
}

class DefaultResource extends AbstractResource implements Resource {

    private boolean inited = false;

    @Override
    public void processInit() throws CannotInitResourceException {
        inited = true;
    }

    @Override
    public void processDestroy() throws CannotDestroyResourceException {
        inited = false;
    }

    public boolean isInited() {
        return inited;
    }
}
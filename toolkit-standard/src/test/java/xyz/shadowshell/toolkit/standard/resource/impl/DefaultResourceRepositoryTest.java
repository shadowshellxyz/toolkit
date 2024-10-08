package xyz.shadowshell.toolkit.standard.resource.impl;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import xyz.shadowshell.toolkit.standard.repository.exception.ObjectRepositoryException;
import xyz.shadowshell.toolkit.standard.resource.Resource;
import xyz.shadowshell.toolkit.standard.resource.ResourceRepository;
import xyz.shadowshell.toolkit.standard.resource.abstracts.AbstractResource;
import xyz.shadowshell.toolkit.standard.resource.exception.CannotDestroyResourceException;
import xyz.shadowshell.toolkit.standard.resource.exception.CannotInitResourceException;

/**
 * ResourceRepositoryTest
 *
 * @author shadow
 */
public class DefaultResourceRepositoryTest {

    @Test
    public void test() {

        String expectedObjectId = "objectId";

        ResourceRepository objectRepository = new DefaultResourceRepository();

        objectRepository.lookup("");
        objectRepository.lookup(null);

        Resource expected = new AbstractResource() {

            @Override
            public void processInit() throws CannotInitResourceException {

            }

            @Override
            public void processDestroy() throws CannotDestroyResourceException {

            }
        };
        boolean actualFlag = false;

        //register
        try {
            objectRepository.register("", null);
        } catch (ObjectRepositoryException e) {
            actualFlag = true;
        }
        Assert.assertTrue(actualFlag);
        actualFlag = false;
        try {
            objectRepository.register(null, null);
        } catch (ObjectRepositoryException e) {
            actualFlag = true;
        }
        Assert.assertTrue(actualFlag);
        actualFlag = false;
        try {
            objectRepository.register(expectedObjectId, null);
        } catch (ObjectRepositoryException e) {
            actualFlag = true;
        }
        Assert.assertTrue(actualFlag);

        //unregister
        objectRepository.unregister("");
        objectRepository.unregister(null);

        Resource actual = objectRepository.lookup(expectedObjectId);
        Assert.assertNull(actual);

        objectRepository.register(expectedObjectId, expected);
        actual = objectRepository.lookup(expectedObjectId);
        Assert.assertEquals(actual, expected);

        Collection<Resource> actuals = (Collection<Resource>) objectRepository.lookupAll();
        Assert.assertEquals(actuals.size(), 1);
        Assert.assertEquals(actuals.iterator().next(), expected);
        try {
            actuals.add(expected);
        } catch (UnsupportedOperationException e) {
            actualFlag = true;
        }
        Assert.assertTrue(actualFlag);

        objectRepository.unregister(expectedObjectId);
        actual = objectRepository.lookup(expectedObjectId);
        Assert.assertNull(actual);
    }

    @Test
    public void buildKey() {

        String groupId = "groupId";
        String resourceId = "resourceId";

        ResourceRepository objectRepository = new DefaultResourceRepository();
        String actual = objectRepository.buildKey(groupId, resourceId);
        Assert.assertEquals(String.format("%s:%s", groupId, resourceId), actual);
    }
}

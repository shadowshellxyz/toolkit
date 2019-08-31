package org.walkerljl.toolkit.standard.repository.abstracts;

import java.io.Serializable;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.toolkit.standard.BaseUnitTest;
import org.walkerljl.toolkit.standard.repository.ObjectRepository;
import org.walkerljl.toolkit.standard.repository.exception.ObjectRepositoryException;

/**
 * AbstractObjectRepositoryTest
 *
 * @author xingxun
 */
public class AbstractObjectRepositoryTest extends BaseUnitTest {

    @Test
    public void test() {

        String expectedObjectId = "objectId";

        ObjectRepository<String, User> objectRepository = new AbstractObjectRepository<String, User>() {

        };

        objectRepository.lookup("");
        objectRepository.lookup(null);

        User expected = new User(expectedObjectId);
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

        User actual = objectRepository.lookup(expectedObjectId);
        Assert.assertNull(actual);

        objectRepository.register(expectedObjectId, expected);
        actual = objectRepository.lookup(expectedObjectId);
        Assert.assertEquals(actual, expected);

        Collection<User> actuals = (Collection<User>)objectRepository.lookupAll();
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

}

class User implements Serializable {
    private String id;

    public User(String id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>id</tt>.
     *
     * @return property value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     *
     * @param id  value to be assigned to property id
     */
    public void setId(String id) {
        this.id = id;
    }
}

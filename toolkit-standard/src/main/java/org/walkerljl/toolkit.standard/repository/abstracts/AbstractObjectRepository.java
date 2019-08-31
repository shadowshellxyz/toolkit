package org.walkerljl.toolkit.standard.repository.abstracts;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.walkerljl.toolkit.standard.exception.AppException;
import org.walkerljl.toolkit.standard.repository.ObjectRepository;
import org.walkerljl.toolkit.standard.repository.exception.ObjectRepositoryException;

/**
 * 抽象的仓储实现
 *
 * @author xingxun
 */
public class AbstractObjectRepository<KEY, VALUE> implements ObjectRepository<KEY, VALUE> {

    private final ConcurrentMap<KEY, VALUE> repository = new ConcurrentHashMap<>();

    @Override
    public void register(KEY key, VALUE value) {
        assertKeyNotNull(key);
        assertValueNotNull(value);
        repository.put(key, value);
    }

    @Override
    public void unregister(KEY key) {
        if (key == null) {
            return;
        }
        repository.remove(key);
    }

    @Override
    public VALUE lookup(KEY key) {
        if (key == null) {
            return null;
        }
        return repository.get(key);
    }

    @Override
    public Collection<? extends VALUE> lookupAll() {
        return Collections.unmodifiableCollection(repository.values());
    }

    /**
     * 断言对象Key不能为Null
     *
     * @param key 对象Key
     */
    private void assertKeyNotNull(KEY key) {
        if (key == null) {
            throw new ObjectRepositoryException("Object key is null.");
        }
    }

    /**
     * 断言对象不能为Null
     *
     * @param value 对象
     */
    private void assertValueNotNull(VALUE value) {
        if (value == null) {
            throw new ObjectRepositoryException("Object value is null.");
        }
    }
}
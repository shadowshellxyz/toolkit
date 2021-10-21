package net.shadowdefender.toolkit.template.cache;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shadow
 */
public interface JvmCacheDataAccessTemplate<E> extends CacheDataAccessTemplate<E> {

    static final Map<String, String> CACHE = new HashMap<>();

    /**
     * get object
     *
     * @param key  key
     * @param type object type
     * @return value
     */
    default E getObjectFromCache(String key, Class<E> type) {
        String cacheText = CACHE.get(key);
        if (cacheText == null || "".equalsIgnoreCase(cacheText)) {
            return null;
        }

        return deserialize(cacheText, type);
    }

    /**
     * put object
     *
     * @param key    key
     * @param object object
     * @param ttl time to live, unit seconds
     */
    default void putObjectToCache(String key, E object, int ttl) {
        CACHE.put(key, serialize(object));
    }

    /**
     * serialize
     *
     * @param object object
     * @return String
     */
    default String serialize(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * deserialize
     *
     * @param string string
     * @param type   type
     * @return object
     */
    default E deserialize(String string, Class<E> type) {
        return JSON.parseObject(string, type);
    }

    /**
     * is debug enabled
     *
     * @return true:enabled
     */
    default boolean isDebugEnabled() {
        return true;
    }

    /**
     * debug
     *
     * @param message message
     */
    default void debug(String message) {
        System.out.println(message);
    }
}
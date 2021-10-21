package net.shadowdefender.toolkit.template.cache;

import java.text.MessageFormat;

/**
 * Cache data access template
 *
 * @author shadow
 */
public interface CacheDataAccessTemplate<E> extends CacheSource<E> {

    /**
     * get object
     *
     * @param params param list
     * @return object
     */
    default E getObject(Object... params) {

        // param validate
        if (params == null || params.length == 0) {
            if (isDebugEnabled()) {
                debug("Query param list is null or empty, return null.");
            }
            return null;
        }

        // build cache key
        String cacheKey = buildCacheKey(params);
        if (cacheKey == null || "".equalsIgnoreCase(cacheKey.trim())) {
            if (isDebugEnabled()) {
                debug(MessageFormat.format("Cache key is blank, param list is {0}.", serialize(params)));
            }
            return null;
        }

        // get cache value
        E value = getObjectFromCache(cacheKey, getObjectType());
        if (value != null) {
            if (isDebugEnabled()) {
                debug(MessageFormat.format("[HIT CACHE], key is {0}, param list is {1}, value is {2}.",
                        cacheKey, serialize(params), serialize(value)));
            }
            return value;
        }

        // check is contain cache key
        if (containsCacheKey(cacheKey)) {
            if (isDebugEnabled()) {
                debug(MessageFormat.format("Cache value is null, but cache key is exists, return null, cache key is {0}, param list is {1}.",
                        cacheKey, serialize(params)));
            }
            return null;
        }

        // load data from datasource
        value = loadDataFromDataSource(params);

        // put cache value when value is null
        if (value == null) {

            //put key
            putKeyToCache(cacheKey, ttl().getTtlValue());
            if (isDebugEnabled()) {
                debug(MessageFormat.format("Cache value is null from dataSource, put key {0} to cache, param list is {1}.",
                        cacheKey, serialize(params)));
            }
        } else {

            // put cache data
            putObjectToCache(cacheKey, value, ttl().getTtlValue());
            if (isDebugEnabled()) {
                debug(MessageFormat.format("Put value to cache which comes from dataSource, param list is {0}, cache key is {1}, cache value is {2}.",
                        serialize(params), serialize(cacheKey), serialize(value)));
            }
        }

        // debug
        if (isDebugEnabled()) {
            debug(MessageFormat.format("[HIT DATASOURCE], param list is {0}, value is {1}.",
                    serialize(params), serialize(value)));
        }

        return value;
    }

    /**
     * is debug enabled
     *
     * @return true:enabled
     */
    default boolean isDebugEnabled() {
        return false;
    }

    /**
     * debug
     *
     * @param message message
     */
    default void debug(String message) {
        //do nothing
    }

    /**
     * load data form other data source
     *
     * @param params param list
     * @return value
     */
    E loadDataFromDataSource(Object... params);

}

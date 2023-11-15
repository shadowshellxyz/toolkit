package xyz.shadowshell.toolkit.template.cache;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

/**
 * cache source
 *
 * @author shadow
 */
public interface CacheSource<E> {

    /**
     * default cache key item separator
     */
    static final String DEFAULT_CACHE_KEY_ITEM_SEPARATOR = ":";

    /**
     * get object type
     *
     * @return object type
     */
    @SuppressWarnings("unchecked")
    default Class<E> getObjectType() {
        return (Class<E>) ((ParameterizedType) getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
    }

    /**
     * build key
     *
     * @param params params
     * @return key
     */
    default String buildCacheKey(Object... params) {

        if (params == null || params.length == 0) {
            return "";
        }

        // param list iterator
        Iterator<Object> paramsIterator = Arrays.stream(params).iterator();

        // convert array to string
        StringBuilder paramsStringBuilder = new StringBuilder();
        paramsStringBuilder.append(paramsIterator.next());
        while (paramsIterator.hasNext()) {
            paramsStringBuilder.append(getCacheKeyItemSeparator()).append(paramsIterator.next());
        }

        // build
        return MessageFormat.format("{0}{1}{2}{3}{4}",
                getCacheKeyPrefix(),
                getCacheKeyItemSeparator(),
                getEnvInfoCode(),
                getCacheKeyItemSeparator(),
                paramsStringBuilder.toString()
        );
    }

    /**
     * get cache key prefix
     *
     * @return cache prefix
     */
    String getCacheKeyPrefix();

    /**
     * get cache key item separator
     *
     * @return cache key item separator
     */
    default String getCacheKeyItemSeparator() {
        return DEFAULT_CACHE_KEY_ITEM_SEPARATOR;
    }

    /**
     * get env info code
     *
     * @return env info code
     */
    default String getEnvInfoCode() {
        return "LOCAL";
    }

    /**
     * check contain key
     *
     * @param key key
     * @return true:contain;false:not contain
     */
    default boolean containsCacheKey(String key) {
        return getObjectFromCache(key) != null;
    }

    /**
     * put key
     *
     * @param key key
     * @param ttl time to live, unit seconds
     */
    default void putKeyToCache(String key, int ttl) {
        putObjectToCache(key, null, ttl);
    }

    /**
     * get object
     *
     * @param key key
     * @return value
     */
    default E getObjectFromCache(String key) {
        return getObjectFromCache(key, getObjectType());
    }

    /**
     * get object
     *
     * @param key  key
     * @param type object type
     * @return value
     */
    E getObjectFromCache(String key, Class<E> type);

    /**
     * put object
     *
     * @param key    key
     * @param object object
     * @param ttl    time to live, unit seconds
     */
    void putObjectToCache(String key, E object, int ttl);

    /**
     * ttl
     *
     * @return time to live, unit seconds
     */
    CacheTtl ttl();

    /**
     * cache ttl
     */
    class CacheTtl implements Serializable {

        /**
         * min value
         */
        private Integer min;

        /**
         * max value
         */
        private Integer max;

        /**
         * value
         */
        private final int value;

        /**
         * constructor
         *
         * @param min min value
         * @param max max value
         */
        public CacheTtl(Integer min, Integer max) {
            this.min = min;
            this.max = max;

            Random random = new Random();
            value = random.nextInt((max - min) + 1) + min;
        }

        /**
         * get ttl value
         *
         * @return ttl value
         */
        public int getTtlValue() {
            return value;
        }

        /**
         * Getter method for property <tt>min</tt>.
         *
         * @return property value of min
         */
        public Integer getMin() {
            return min;
        }

        /**
         * Setter method for property <tt>min</tt>.
         *
         * @param min value to be assigned to property min
         */
        public void setMin(Integer min) {
            this.min = min;
        }

        /**
         * Getter method for property <tt>max</tt>.
         *
         * @return property value of max
         */
        public Integer getMax() {
            return max;
        }

        /**
         * Setter method for property <tt>max</tt>.
         *
         * @param max value to be assigned to property max
         */
        public void setMax(Integer max) {
            this.max = max;
        }
    }

    /**
     * serialize
     *
     * @param object object
     * @return String
     */
    String serialize(Object object);

    /**
     * deserialize
     *
     * @param string string
     * @param type   type
     * @return object
     */
    E deserialize(String string, Class<E> type);

}
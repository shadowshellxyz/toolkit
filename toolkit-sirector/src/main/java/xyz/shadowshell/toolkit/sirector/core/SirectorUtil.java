package xyz.shadowshell.toolkit.sirector.core;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author shadow
 */
class SirectorUtil {

    public static <E> int size(Collection<E> collection) {

        return collection == null ? 0 : collection.size();
    }

    public static <E> boolean isCollectionEmpty(Collection<E> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <E> boolean isCollectionNotEmpty(Collection<E> collection) {
        return collection != null && !collection.isEmpty();
    }

    public static <K, V> boolean isMapEmpty(Map<K, V> map) {
        return map == null || map.isEmpty();
    }

    public static <K, V> boolean isMapNotEmpty(Map<K, V> map) {
        return map != null && !map.isEmpty();
    }

    public static <K, V> Map<K, List<V>> copyMap(Map<K, List<V>> map) {

        if (isMapEmpty(map)) {
            return null;
        }

        Map<K, List<V>> dest = new HashMap<>();
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {

            List<V> elements = entry.getValue();
            if (isCollectionEmpty(elements)) {
                continue;
            }
            dest.put(entry.getKey(), new ArrayList<>(elements));
        }

        return dest;
    }

    public static <E> void copy(Collection<E> src, Collection<E> dest) {

        if (isCollectionEmpty(src)) {
            return;
        }
        if (dest == null) {
            return;
        }

        dest.addAll(src);
    }

    public static <K, V> Map<K, V> copyKeys(Collection<K> keys, Map<K, V> dest) {

        if (isCollectionEmpty(keys)) {
            return dest;
        }
        if (isMapEmpty(dest)) {
            return dest;
        }

        for (K key : keys) {
            dest.put(key, null);
        }

        return dest;
    }

    public static <K, V> void fillDefaultValueIfEmpty(Map<K, List<V>> map, V defaultValue, Consumer<K> calllback) {

        if (isMapEmpty(map)) {
            return;
        }

        for (Map.Entry<K, List<V>> entry : map.entrySet()) {

            K key = entry.getKey();
            List<V> elements = entry.getValue();
            if (isCollectionEmpty(elements)) {
                elements = new ArrayList<>(1);
                map.put(entry.getKey(), elements);
                elements.add(defaultValue);

                if (calllback != null) {
                    calllback.accept(key);
                }
            }
        }
    }

    public static <E> Map<E, List<E>> reverseCopyMap(Map<E, List<E>> src, Map<E, List<E>> dest) {

        if (isMapEmpty(src)) {
            return dest;
        }

        if (isMapEmpty(dest)) {
            return dest;
        }

        for (Map.Entry<E, List<E>> entry : src.entrySet()) {

            E srcKey = entry.getKey();
            List<E> srcPartValues = entry.getValue();
            if (isCollectionEmpty(srcPartValues)) {
                continue;
            }

            for (E srcPartValue : srcPartValues) {
                List<E> destPartValues = dest.get(srcPartValue);
                if (destPartValues == null) {
                    destPartValues = new ArrayList<>(1);
                    dest.put(srcPartValue, destPartValues);
                }
                destPartValues.add(srcKey);
            }
        }

        return dest;
    }

}
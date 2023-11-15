package xyz.shadowshell.toolkit.db.api.hash;

/**
 * Hashing
 *
 * @author shadow
 */
public interface Hashing {

    /**
     * @param object
     * @param length
     * @return
     */
    int indexFor(Object object, int length);
}

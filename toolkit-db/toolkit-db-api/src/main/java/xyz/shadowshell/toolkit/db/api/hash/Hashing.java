package xyz.shadowshell.toolkit.db.api.hash;

/**
 * Hashing
 *
 * @author shadowwalkerxyz@qq.com<wwww.shadowshell.xyz>
 */
public interface Hashing {

    /**
     * @param object
     * @param length
     * @return
     */
    int indexFor(Object object, int length);
}

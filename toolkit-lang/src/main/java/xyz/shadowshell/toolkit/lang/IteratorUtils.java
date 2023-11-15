package xyz.shadowshell.toolkit.lang;

import java.util.Iterator;

/**
 * IteratorUtils
 *
 * @author shadow
 */
public final class IteratorUtils {

    private IteratorUtils() {
    }

    public static String wrap(Iterator<?> objects, String prefixWrapper, String suffixWrapper, String sepeator) {
        if (objects == null) {
            return StringPool.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        if (objects.hasNext()) {
            sb.append(prefixWrapper).append(objects.next().toString()).append(suffixWrapper);
        }
        while (objects.hasNext()) {
            sb.append(sepeator).append(prefixWrapper).append(objects.next().toString()).append(suffixWrapper);
        }
        return sb.toString();
    }
}

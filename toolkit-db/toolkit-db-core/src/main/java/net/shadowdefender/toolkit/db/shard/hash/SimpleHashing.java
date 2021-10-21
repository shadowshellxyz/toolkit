package net.shadowdefender.toolkit.db.shard.hash;

import net.shadowdefender.toolkit.db.api.hash.Hashing;

/**
 * SimpleHashing
 *
 * @author shadow
 */
public class SimpleHashing implements Hashing {

    private int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private int index(int h, int length) {
        return h & (length - 1);
    }

    @Override
    public int indexFor(Object obj, int length) {
        return index(hash(obj.hashCode()), length);
    }
}

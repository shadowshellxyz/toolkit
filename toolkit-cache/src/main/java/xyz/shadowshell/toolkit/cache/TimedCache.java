package xyz.shadowshell.toolkit.cache;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * TimedCache
 *
 * @author shadow
 */
public class TimedCache<K, V> extends AbstractCacheMap<K, V> {

    public TimedCache(long timeout) {
        this.cacheSize = 0;
        this.timeout = timeout;
        cacheMap = new HashMap<K, CacheObject<K, V>>();
    }

    /**
     * Prunes expired elements from the cache. Returns the number of removed objects.
     */
    @Override
    protected int pruneCache() {
        int count = 0;
        Iterator<CacheObject<K, V>> values = cacheMap.values().iterator();
        while (values.hasNext()) {
            CacheObject<K, V> co = values.next();
            if (co.isExpired()) {
                values.remove();
                count++;
            }
        }
        return count;
    }

    protected ScheduledExecutorService scheduledExecutorService;

    /**
     * Schedules prune.
     */
    public void schedulePrune(long delay) {
        if (scheduledExecutorService == null) {
            scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        }
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    prune();
                } catch (Throwable e) {
                    //ignore
                }
            }
        }, delay, TimeUnit.SECONDS);
    }

    /**
     * Cancels prune schedules.
     */
    public void cancelPruneSchedule() {
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
        }
    }
}
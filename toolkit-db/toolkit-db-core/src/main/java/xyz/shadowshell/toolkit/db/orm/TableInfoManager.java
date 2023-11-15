/*
 * Copyright (c) 2010-2021 www.shadowdefender.net All Rights Reserved.
 */
package xyz.shadowshell.toolkit.db.orm;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


import net.shadowdefender.toolkit.cache.Cache;
import net.shadowdefender.toolkit.cache.LRUCache;
import xyz.shadowshell.toolkit.db.orm.entity.TableInfo;
import xyz.shadowshell.toolkit.db.orm.parse.TableInfoParser;
import net.shadowdefender.toolkit.logging.Logger;
import net.shadowdefender.toolkit.logging.LoggerFactory;

/**
 * TableInfoManager
 *
 * @author shadow
 */
public class TableInfoManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(TableInfoManager.class);

    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    private final Cache<Class<?>, TableInfo> tableCache = new LRUCache<>(100);

    private TableInfoManager() {
    }

    public static TableInfoManager getInstance() {
        return TableManagerHolder.instance;
    }

    public TableInfo getTable(Class<?> entityClass) {
        readLock.lock();
        TableInfo tableInfo = null;
        try {
            tableInfo = tableCache.get(entityClass);
            if (tableInfo == null) {
                readLock.unlock();
                writeLock.lock();
                try {
                    if (tableInfo == null) {
                        tableInfo = TableInfoParser.parse(entityClass);
                        if (tableInfo != null) {
                            tableCache.put(entityClass, tableInfo);
                        }
                        if (LOGGER.isDebugEnabled()) {
                            LOGGER.debug(String.format("Parse entity:%s", entityClass));
                        }
                    }
                } finally {
                    writeLock.unlock();
                    readLock.lock();
                }
            }
        } finally {
            readLock.unlock();
        }
        return tableInfo;
    }

    private static class TableManagerHolder {
        private static TableInfoManager instance = new TableInfoManager();
    }
}
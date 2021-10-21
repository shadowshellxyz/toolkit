/*
 * Copyright (c) 2010-2021 www.shadowdefender.net All Rights Reserved.
 */
package net.shadowdefender.toolkit.db.orm.sql;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.shadowdefender.toolkit.lang.SetUtils;

/**
 * EntityQuery
 *
 * @author shadow
 */
public class EntityQuery extends AbstractEntityQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    private StringBuilder sql = new StringBuilder();
    private Map<String, Object> parameters = new HashMap<String, Object>();
    private Set<String> ignoredProperties = SetUtils.newHashSet();
    //private boolean ignoreAll = false;

    public static EntityQuery createQuery() {
        return new EntityQuery();
    }

    public static EntityQuery createQuery(Class<?> clazz) {
        EntityQuery entityQuery = createQuery();
        return entityQuery;
    }

    public EntityQuery parameter(String key, Object value) {
        if (null != key && !"".equals(key) && value != null) {
            parameters.put(key, value);
        }
        //ignoreAll = true;
        return this;
    }

    public EntityQuery ignore(String propertyName) {
        ignoredProperties.add(propertyName);
        return this;
    }

    public EntityQuery order() {
        sql.append(" order by");
        return this;
    }

    public EntityQuery asc(String columnName) {
        if (columnName != null && !"".equals(columnName)) {
            sql.append(" ").append(columnName).append(" asc");
        }
        return this;
    }

    public EntityQuery desc(String columnName) {
        if (columnName != null && !"".equals(columnName)) {
            sql.append(" ").append(columnName).append(" desc");
        }
        return this;
    }

    public EntityQuery limit(int startIndex, int pageSize) {
        sql.append(" limit ").append(startIndex).append(", ").append(pageSize);
        return this;
    }

    public String toSql() {
        return sql.toString();
    }
}
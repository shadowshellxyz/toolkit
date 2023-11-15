/*
 * Copyright (c) 2010-2021 www.shadowdefender.net All Rights Reserved.
 */
package xyz.shadowshell.toolkit.db.orm.sql;

/**
 * AbstractEntityQuery
 *
 * @author shadow
 */
public abstract class AbstractEntityQuery {

    private StringBuilder sql = new StringBuilder();

    public AbstractEntityQuery alias(String aliasName) {
        sql.append(" ").append(aliasName);
        return this;
    }

    public AbstractEntityQuery where() {
        sql.append(" where");
        return this;
    }

    public AbstractEntityQuery and(String propertyName, Object value) {
        return this;
    }

    public AbstractEntityQuery or(String propertyName, Object value) {
        return this;
    }
}
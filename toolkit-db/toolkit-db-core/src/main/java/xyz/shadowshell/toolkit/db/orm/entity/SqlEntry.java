package xyz.shadowshell.toolkit.db.orm.entity;

import java.util.Arrays;

/**
 * SqlEntry
 *
 * @author shadow
 */
public class SqlEntry {

    /**
     * SQL
     */
    private String sql;
    /**
     * 参数
     */
    private Object[] params;

    /**
     * 构造函数
     *
     * @param sql
     * @param params
     */
    public SqlEntry(String sql, Object[] params) {
        this.sql = sql;
        this.params = params;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "SqlEntry{" +
                "sql='" + sql + '\'' +
                ", params=" + Arrays.toString(params) +
                '}';
    }
}
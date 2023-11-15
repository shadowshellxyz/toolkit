package xyz.shadowshell.toolkit.db.orm.entity;

import java.io.Serializable;

import xyz.shadowshell.toolkit.db.orm.enums.DatabaseType;

/**
 * Database
 *
 * @author shadow
 */
public class DatabaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据库类型
     */
    private DatabaseType type;
    /**
     * 名称
     */
    private String name;
    /**
     * 字符集
     */
    private String charset;
    /**
     * 引擎
     */
    private String engine;

    public DatabaseInfo() {
    }

    public DatabaseType getType() {
        return type;
    }

    public void setType(DatabaseType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "DatabaseInfo{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", charset='" + charset + '\'' +
                ", engine='" + engine + '\'' +
                '}';
    }
}
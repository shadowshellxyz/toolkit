package xyz.shadowshell.toolkit.db.orm.sql;

import xyz.shadowshell.toolkit.db.DbException;

/**
 * SQL生成异常
 *
 * @author shadow
 */
public class SqlGenerateException extends DbException {

    private static final long serialVersionUID = -6786549876849535944L;

    /**
     * 默认构造函数
     */
    public SqlGenerateException() {
        super();
    }

    /**
     * 构造函数
     *
     * @param message
     */
    public SqlGenerateException(String message) {
        super(message);
    }

    /**
     * 构造函数
     *
     * @param e
     */
    public SqlGenerateException(Throwable e) {
        super(e);
    }

    /**
     * 构造函数
     *
     * @param msg
     * @param e
     */
    public SqlGenerateException(String msg, Throwable e) {
        super(msg, e);
    }
}
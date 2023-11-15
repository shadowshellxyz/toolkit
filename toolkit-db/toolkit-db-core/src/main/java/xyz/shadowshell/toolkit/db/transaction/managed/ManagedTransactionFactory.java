package xyz.shadowshell.toolkit.db.transaction.managed;

import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import xyz.shadowshell.toolkit.db.transaction.Transaction;
import xyz.shadowshell.toolkit.db.transaction.TransactionFactory;
import xyz.shadowshell.toolkit.db.transaction.TransactionIsolationLevel;

/**
 * ManagedTransactionFactory
 *
 * @author shadow
 */
public class ManagedTransactionFactory implements TransactionFactory {

    private boolean closeConnection = true;

    public void setProperties(Properties props) {
        if (props != null) {
            String closeConnectionProperty = props.getProperty("closeConnection");
            if (closeConnectionProperty != null) {
                closeConnection = Boolean.valueOf(closeConnectionProperty);
            }
        }
    }

    public Transaction newTransaction(Connection conn) {
        return new ManagedTransaction(conn, closeConnection);
    }

    public Transaction newTransaction(DataSource ds, TransactionIsolationLevel level, boolean autoCommit) {
        // Silently ignores autocommit and isolation level, as managed transactions are entirely
        // controlled by an external manager.  It's silently ignored so that
        // code remains portable between managed and unmanaged configurations.
        return new ManagedTransaction(ds, level, closeConnection);
    }
}
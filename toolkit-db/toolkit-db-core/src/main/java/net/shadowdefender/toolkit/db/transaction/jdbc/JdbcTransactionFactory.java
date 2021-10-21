package net.shadowdefender.toolkit.db.transaction.jdbc;

import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import net.shadowdefender.toolkit.db.transaction.Transaction;
import net.shadowdefender.toolkit.db.transaction.TransactionFactory;
import net.shadowdefender.toolkit.db.transaction.TransactionIsolationLevel;

/**
 * Creates {@link JdbcTransaction} instances.
 *
 * @see JdbcTransaction
 */
public class JdbcTransactionFactory implements TransactionFactory {

    public void setProperties(Properties props) {

    }

    public Transaction newTransaction(Connection conn) {
        return new JdbcTransaction(conn);
    }

    public Transaction newTransaction(DataSource ds, TransactionIsolationLevel level, boolean autoCommit) {
        return new JdbcTransaction(ds, level, autoCommit);
    }
}
package xyz.shadowshell.toolkit.db.orm.session;

import javax.sql.DataSource;

import xyz.shadowshell.toolkit.db.orm.enums.DatabaseType;

/**
 * Configuration
 *
 * @author shadow
 */
public class Configuration {

    private DataSource dataSource;
    private DatabaseType databaseType;

    public Configuration() {
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DatabaseType getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(DatabaseType databaseType) {
        this.databaseType = databaseType;
    }
}

package xyz.shadowshell.toolkit.db.ds;

import javax.sql.DataSource;

/**
 * DataSourceFactory
 *
 * @author shadow
 */
public interface DataSourceFactory {

    /**
     * 获取数据源
     *
     * @return 数据源
     */
    DataSource getDataSource();
}

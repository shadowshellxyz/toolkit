package xyz.shadowshell.toolkit.db.ds.impl.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import net.shadowdefender.toolkit.db.ds.abstracts.AbstractDataSourceFactory;
import net.shadowdefender.toolkit.logging.Logger;
import net.shadowdefender.toolkit.logging.LoggerFactory;

import java.beans.PropertyVetoException;

/**
 * C3P0 数据源
 *
 * @author shadowwalkerxyz@qq.com<wwww.shadowshell.xyz>
 */
public class C3P0DataSourceFactory extends AbstractDataSourceFactory<ComboPooledDataSource> {

    private static final Logger LOG = LoggerFactory.getLogger(C3P0DataSourceFactory.class);

    @Override
    public ComboPooledDataSource createDataSource() {
        return new ComboPooledDataSource();
    }

    @Override
    public void setDriver(ComboPooledDataSource ds, String driver) {
        try {
            ds.setDriverClass(driver);
        } catch (PropertyVetoException e) {
            LOG.error("错误：初始化 JDBC Driver Class 失败！", e);
        }
    }

    @Override
    public void setUrl(ComboPooledDataSource ds, String url) {
        ds.setJdbcUrl(url);
    }

    @Override
    public void setUsername(ComboPooledDataSource ds, String username) {
        ds.setUser(username);
    }

    @Override
    public void setPassword(ComboPooledDataSource ds, String password) {
        ds.setPassword(password);
    }

    @Override
    public void setAdvancedConfig(ComboPooledDataSource ds) {

    }
}
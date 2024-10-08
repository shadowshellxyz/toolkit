package xyz.shadowshell.toolkit.db.ds.abstracts;

import javax.sql.DataSource;

import xyz.shadowshell.toolkit.db.ds.DataSourceFactory;
import net.shadowdefender.configuration.client.ConfiguratorFactory;

/**
 * AbstractDataSourceFactory
 *
 * @author shadow
 */
public abstract class AbstractDataSourceFactory<T extends DataSource> implements DataSourceFactory {

    @Override
    public final T getDataSource() {
        // 创建数据源对象
        T ds = createDataSource();
        // 设置基础属性
        setDriver(ds, getPropertyAsString("org.walkerljl.db.jdbc.driver"));
        setUrl(ds, getPropertyAsString("org.walkerljl.db.jdbc.url"));
        setUsername(ds, getPropertyAsString("org.walkerljl.db.jdbc.username"));
        setPassword(ds, getPropertyAsString("org.walkerljl.db.jdbc.password"));
        // 设置高级属性
        setAdvancedConfig(ds);
        return ds;
    }

    protected String getPropertyAsString(String key) {
        return ConfiguratorFactory.getStdConfigurator().getAsString(key);
    }

    public abstract T createDataSource();

    public abstract void setDriver(T ds, String driver);

    public abstract void setUrl(T ds, String url);

    public abstract void setUsername(T ds, String username);

    public abstract void setPassword(T ds, String password);

    public abstract void setAdvancedConfig(T ds);
}
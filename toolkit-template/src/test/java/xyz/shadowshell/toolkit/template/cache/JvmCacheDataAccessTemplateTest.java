package xyz.shadowshell.toolkit.template.cache;


import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * UserCacheDataAccess
 *
 * @author shadow
 */
public class JvmCacheDataAccessTemplateTest {

    @Test
    public void getObject() {

        JvmCacheDataAccessTemplate cacheDataAccessTemplate = new JvmCacheDataAccessTemplate<User>() {

            @Override
            public String getEnvInfoCode() {
                return "DEV";
            }

            @Override
            public CacheSource.CacheTtl ttl() {
                return new CacheTtl(5 * 60, 8 * 60);
            }

            @Override
            public String getCacheKeyPrefix() {
                return "shadowdefender.net";
            }

            @Override
            public User loadDataFromDataSource(Object... params) {
                return new User("shadowdefender", "哈哈");
            }

        };

        System.out.println(JSON.toJSONString(cacheDataAccessTemplate.getObject("shadowdefender")));
        System.out.println("=============");
        System.out.println(JSON.toJSONString(cacheDataAccessTemplate.getObject("shadowdefender")));
        System.out.println("=============");
        System.out.println(JSON.toJSONString(cacheDataAccessTemplate.getObject("shadowdefenderx")));
        System.out.println("=============");
        System.out.println(JSON.toJSONString(cacheDataAccessTemplate.getObject("shadowdefenderx")));

    }
}

class User {
    private String id;
    private String name;

    public User() {
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Getter method for property <tt>id</tt>.
     *
     * @return property value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     *
     * @param id value to be assigned to property id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     *
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }
}
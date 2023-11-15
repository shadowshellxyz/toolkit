package xyz.shadowshell.toolkit.db.orm;

import org.junit.Test;

/**
 * SqlBuilderTest
 *
 * @author shadow
 */
public class SqlBuilderTest {

    @Test
    public void generateSql() {
        OrmTestBean bean = new OrmTestBean();
        bean.setId(1L);
        //System.out.println(SqlBuilder.generateInsertSql(bean));
        //System.out.println(SqlBuilder.generateDeleteByIdSql(bean));
    }
}

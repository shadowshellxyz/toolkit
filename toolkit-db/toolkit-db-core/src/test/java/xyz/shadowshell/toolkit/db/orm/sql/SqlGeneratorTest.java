package xyz.shadowshell.toolkit.db.orm.sql;

import xyz.shadowshell.toolkit.db.orm.entity.identity.User;
import org.junit.Test;
import xyz.shadowshell.toolkit.db.orm.entity.SqlEntry;
import xyz.shadowshell.toolkit.db.orm.enums.DatabaseType;
import xyz.shadowshell.toolkit.db.orm.sql.support.MySQLSqlGenerator;
import net.shadowdefender.toolkit.logging.Logger;
import net.shadowdefender.toolkit.logging.LoggerFactory;

import java.util.Date;

/**
 * SqlGeneratorTest
 *
 * @author shadow
 */
public class SqlGeneratorTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SqlGeneratorTest.class);

    @Test
    public void generateBatchInsertSql() {
        User[] users = new User[5];
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setId((long) i);
            user.setUserId("jarvis" + i);
            user.setUserName("JARVIS" + i);
            user.setSex("m");
            user.setEmail("xxx@163.com" + i);
            user.setMobile("10000000" + i);
            user.setTelephone("xxxxxxx" + i);
            user.setBirthday(new Date());
            user.setIdCardNumber("100" + i);
            Date date = new Date();
            user.setLastLoginDate(date);
            user.setRemark("测试" + i);
            user.setStatus(1 + i);
            user.setCreateDate(date);
            user.setUserId("jarvis" + i);
            user.setUserName("JARVIS" + i);
            user.setLastModifyDate(date);
            user.setLastModifyUserId("jarvis" + i);
            user.setLastModifyUserName("JARVIS" + i);

            users[i] = user;
        }

        //print("generateBatchInsertSql", SqlGenerator.generateBatchInsertSql(users));
    }

    @Test
    public void generateDeleteByKeysSql() {
        //print("generateDeleteByKeysSql", SqlGenerator.generateDeleteByKeysSql(User.class, new Integer[]{1,2,3,4,5}));
    }

    @Test
    public void generateDeleteSql() {
        User condition = new User();
        condition.setId(100L);
        condition.setUserId("userId");
        condition.setUserName("userName");
        //print("generateDeleteSql", SqlGenerator.generateDeleteSql(condition));
    }

    @Test
    public void generateUpdateByKeysSql() {
        //print("generateUpdateByKeysSql", SqlGenerator.generateUpdateByKeysSql(new User(), 1));
        User modifiedEntity = new User();
        modifiedEntity.setUserName("aliasUserName");
        //print("generateUpdateByKeysSql", SqlGenerator.generateUpdateByKeysSql(modifiedEntity, new Integer[]{1,2,3,4,5}));
    }

    @Test
    public void generateUpdateSql() {
        User modifiedEntity = new User();
        modifiedEntity.setUserName("aliasUserName");

        User modifiedCondition = new User();
        modifiedCondition.setUserId("userId");
        //print("generateUpdateSql", SqlGenerator.generateUpdateSql(modifiedEntity, modifiedCondition));
    }

    @Test
    public void generateSelectByKeysSql() {
        //print("generateSelectByKeysSql", SqlGenerator.generateSelectByKeysSql(User.class, new Integer[]{1,2,3,4,5}));
    }

    @Test
    public void generateSelectSql() {
        User user = new User();
        user.setId(1L);
        user.setUserName("xxx");
        System.out.println(new MySQLSqlGenerator(DatabaseType.MYSQL).generateSelectSql(user, 1, 10));
    }

    @Test
    public void generateSelectCountSql() {
        //print("generateSelectCountSql", SqlGenerator.generateSelectCountSql(new User()));
    }

    private void print(String messagePrefix, SqlEntry sqlEntry) {
        if (sqlEntry == null) {
            LOGGER.info(messagePrefix + " sqlEntry is null");
        }
        LOGGER.info(messagePrefix + " sql -> " + (sqlEntry.getSql() == null ? "NULL" : sqlEntry.getSql()));
        StringBuilder paramsString = new StringBuilder();
        if (sqlEntry.getParams() != null) {
            int index = 0;
            for (Object param : sqlEntry.getParams()) {
                if (index != 0) {
                    paramsString.append(",");
                }
                paramsString.append(param == null ? "NULL" : param.toString());
                index++;
            }
        }
        LOGGER.info(messagePrefix + " params -> " + paramsString.toString());
    }
}
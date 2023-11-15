package xyz.shadowshell.toolkit.db.shard.parse.support;

import java.util.List;

import javax.sql.DataSource;

import xyz.shadowshell.toolkit.db.shard.parse.ShardParser;
import xyz.shadowshell.toolkit.db.DbException;
import net.shadowdefender.toolkit.db.api.hash.Hashing;
import xyz.shadowshell.toolkit.db.orm.EntityFieldValueUtils;
import xyz.shadowshell.toolkit.db.orm.TableInfoManager;
import xyz.shadowshell.toolkit.db.orm.entity.ColumnInfo;
import xyz.shadowshell.toolkit.db.orm.entity.TableInfo;
import xyz.shadowshell.toolkit.db.shard.ShardInfo;
import net.shadowdefender.toolkit.lang.CollectionUtils;
import net.shadowdefender.toolkit.logging.Logger;
import net.shadowdefender.toolkit.logging.LoggerFactory;

/**
 * DefaultShardParser
 *
 * @author: ShadowDefender
 */
public class DefaultShardParser implements ShardParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultShardParser.class);

    private ShardInfo shardInfo;
    private Hashing hashing;

    @Override
    public DataSource parseMasterDataSource(Object entity) {
        if (entity == null) {
            throw new DbException("Entity is null.");
        }
        DataSource dataSource = shardInfo.getMasterDataSources().get(getDbIndex(entity));
        return dataSource;
    }

    @Override
    public DataSource parseSalveDataSource(Object entity) {
        if (entity == null) {
            throw new DbException("Entity is null.");
        }
        DataSource dataSource = shardInfo.getSalveDataSources().get(getDbIndex(entity));
        return dataSource;
    }

    private String getShardedKey(Object entity, List<ColumnInfo> shardedColumnInfos) {
        if (CollectionUtils.isEmpty(shardedColumnInfos)) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (ColumnInfo shardedColumnInfo : shardedColumnInfos) {
            Object fieldValue = EntityFieldValueUtils.getFieldValue(entity, shardedColumnInfo);
            if (fieldValue != null) {
                stringBuilder.append(fieldValue);
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public String parseTableName(Object entity) {
        if (entity == null) {
            throw new DbException("Entity is null.");
        }
        TableInfo tableInfo = TableInfoManager.getInstance().getTable(entity.getClass());
        if (tableInfo == null || !tableInfo.isSharded()) {
            return null;
        }
        int index = hashing.indexFor(getShardedKey(entity, tableInfo.getSharedColumnInfos()),
                shardInfo.getAllTableQuantities(tableInfo.getName()));
        long tableIndex = index / shardInfo.getDbQuantities() + 1;//获取表序号(1-tableCount)
        return tableInfo.getName() + tableIndex;
    }

    @Override
    public void setShardInfo(ShardInfo shardInfo) {
        if (shardInfo == null) {
            throw new DbException("shardInfo is null.");
        }
        this.shardInfo = shardInfo;
    }

    @Override
    public void setHashing(Hashing hashing) {
        this.hashing = hashing;
    }

    private int getDbIndex(Object entity) {
        TableInfo tableInfo = TableInfoManager.getInstance().getTable(entity.getClass());
        if (tableInfo == null || !tableInfo.isSharded()) {
            return -1;
        }
        int index = hashing.indexFor(getShardedKey(entity, tableInfo.getSharedColumnInfos()),
                shardInfo.getAllTableQuantities(tableInfo.getName()));
        return index % shardInfo.getDbQuantities();
    }
}

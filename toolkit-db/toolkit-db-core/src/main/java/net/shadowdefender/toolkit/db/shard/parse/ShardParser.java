package net.shadowdefender.toolkit.db.shard.parse;

import javax.sql.DataSource;

import net.shadowdefender.toolkit.db.api.hash.Hashing;
import net.shadowdefender.toolkit.db.shard.ShardInfo;

/**
 * ShardParser
 *
 * @author shadow
 */
public interface ShardParser {

    DataSource parseMasterDataSource(Object entity);

    DataSource parseSalveDataSource(Object entity);

    String parseTableName(Object entity);

    void setShardInfo(ShardInfo shardInfo);

    void setHashing(Hashing hashing);
}

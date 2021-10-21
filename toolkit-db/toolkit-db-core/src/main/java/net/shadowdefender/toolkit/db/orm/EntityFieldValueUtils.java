package net.shadowdefender.toolkit.db.orm;

import net.shadowdefender.toolkit.db.orm.entity.ColumnInfo;
import net.shadowdefender.toolkit.db.orm.entity.TableInfo;
import net.shadowdefender.toolkit.lang.ReflectUtils;
import net.shadowdefender.toolkit.db.DbException;

/**
 * EntityFieldValueUtils
 *
 * @author shadow
 */
public class EntityFieldValueUtils {

    /**
     * 获取字段值
     *
     * @param entity
     * @return
     */
    public static Object[] getFieldValues(Object entity) {
        TableInfo table = TableInfoManager.getInstance().getTable(entity.getClass());
        if (table == null) {
            return null;
        }
        int columnsSize = table.getColumnInfos().size();
        if (columnsSize == 0) {
            return null;
        }

        Object[] fieldValues = new Object[columnsSize];
        for (int i = 0; i < columnsSize; i++) {
            fieldValues[i] = getFieldValue(entity, table.getColumnInfos().get(i));
        }
        return fieldValues;
    }

    /**
     * 获取指定字段值
     *
     * @param entity
     * @param column
     * @return
     */
    public static Object getFieldValue(Object entity, ColumnInfo column) {
        if (column == null) {
            return null;
        }
        try {
            return ReflectUtils.invoke(entity, column.getGetterName());
        } catch (Exception e) {
            throw new DbException(e);
        }
    }
}
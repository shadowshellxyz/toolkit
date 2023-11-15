
package xyz.shadowshell.toolkit.officesuite;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.shadowdefender.toolkit.lang.CollectionUtils;
import xyz.shadowshell.toolkit.officesuite.excel.ExcelUtil;

/**
 * @author shadow
 */
public class Test4 {

    public static void main(String[] args) throws Throwable {

        List<List<Object>> dataList = ExcelUtil.read(new FileInputStream("/Users/walkerljl/Downloads/活动报名列表 2020-01-13 10_46_03.xlsx"),

                0, 0, Integer.MAX_VALUE, true);

        System.out.println(dataList.size());

        Set<String> existKey = new HashSet<>();
        Set<String> removeKeys = new HashSet<>();
        for (List<Object> row : dataList) {
            if (row == null) {
                continue;
            }

            String activityName = ExcelUtil.getString(row.get(0));
            String pid = ExcelUtil.getString(row.get(1));
            String industryName = ExcelUtil.getString(row.get(4));
            String takePartActivityName = ExcelUtil.getString(row.get(8));

            String key = String.format("%s#%s#%s#%s",
                    activityName, pid, industryName, takePartActivityName);

            if (existKey.contains(key)) {
                removeKeys.add(String.format("%s#%s#%s#%s",
                        "五福", pid, industryName, takePartActivityName));
                System.out.println(key);
            } else {
                existKey.add(String.format("%s#%s#%s#%s",
                        "五福", pid, industryName, takePartActivityName));
                existKey.add(String.format("%s#%s#%s#%s",
                        "五福活动（行业分会场）", pid, industryName, takePartActivityName));
            }
        }

        System.out.println(CollectionUtils.wrap(removeKeys, "'", "'", ","));
    }
}
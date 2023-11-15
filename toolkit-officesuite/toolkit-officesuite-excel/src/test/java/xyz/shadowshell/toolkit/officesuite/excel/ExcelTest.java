
package xyz.shadowshell.toolkit.officesuite.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.shadowdefender.toolkit.lang.CollectionUtils;

/**
 * @author shadow
 */
public class ExcelTest {

    public static void main(String[] args) throws FileNotFoundException {

        InputStream ins = new FileInputStream("/Users/walkerljl/Downloads/执行结果1.xlsx");
        List<List<Object>> dataList = ExcelUtil.read(ins, 0, 0, Integer.MAX_VALUE, true);

        Set<String> items = new HashSet<>();

        for (List<Object> row : dataList) {
            items.add(String.valueOf(row.get(0)));
        }

        System.out.println(CollectionUtils.wrap(items.iterator(), "", "", ","));

        System.out.println(items.size());
    }
}
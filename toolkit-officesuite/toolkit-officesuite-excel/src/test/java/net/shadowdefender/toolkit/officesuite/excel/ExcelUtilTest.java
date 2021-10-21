package net.shadowdefender.toolkit.officesuite.excel;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * ExcelUtilTest
 *
 * @author shadow
 */
public class ExcelUtilTest {

    @Test
    public void read() throws FileNotFoundException {

        InputStream ins = new FileInputStream( "/Users/walkerljl/Desktop/test.xlsx");
        List<List<Object>> dataList = ExcelUtil.read(ins, 0, 0, Integer.MAX_VALUE, true);

        assertCellDataList(dataList.get(0), new String[] {"10000001", "张三", "20"});
        assertCellDataList(dataList.get(1), new String[] {"10000002", "李四", "21"});
    }

    private void assertCellDataList(List<Object> actualCellDataList, String[] expectedCellDataList) {

        for (int i = 0; i < expectedCellDataList.length; i++) {
            Assert.assertEquals(expectedCellDataList[i], actualCellDataList.get(i));
            System.out.println(actualCellDataList.get(i));
        }
    }

     @Test
    public void write() throws Exception {
        List<List<Object>> rowDataList = new ArrayList<>(1);
        List<Object> cellDataList = new ArrayList<>(1);
        rowDataList.add(cellDataList);
        cellDataList.add("jarvis");
        OutputStream out = new FileOutputStream("/Users/walkerljl/Desktop/read.xlsx");
        ByteArrayOutputStream ous = ExcelUtil.write("test", new String[] {"username"}, rowDataList);
        ous.writeTo(out);
    }
}
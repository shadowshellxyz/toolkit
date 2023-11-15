package xyz.shadowshell.toolkit.officesuite.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.shadowdefender.toolkit.lang.StringUtils;
import net.shadowdefender.toolkit.lang.io.StreamUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import net.shadowdefender.toolkit.standard.NameValue;

/**
 * ExcelUtil
 *
 * @author shadow
 */
public class ExcelUtil {

    /**
     * 读取Excel数据
     *
     * @param ins
     * @return
     */
    public static List<List<Object>> read(InputStream ins) {
        return ExcelUtil.read(ins, 0, 0, Integer.MAX_VALUE, true);
    }

    /**
     * 读取Excel数据
     *
     * @param ins             数据流
     * @param sheetBeginIndex 工作区索引
     * @param rowBeginIndex   行索引
     * @param rowNumLimit     允许读取的行限制
     * @param isAllowNull     是否允许为空
     * @return
     */
    public static List<List<Object>> read(InputStream ins, int sheetBeginIndex, int rowBeginIndex, int rowNumLimit, boolean isAllowNull) {
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(ins);
        } catch (Throwable e) {
            InternalExcelUtil.wrapAndRethrowException(e, "Fail to read excel.");
        } finally {
            StreamUtils.close(ins);
        }

        Sheet sheet = workbook.getSheetAt(sheetBeginIndex);
        int rowCount = sheet.getLastRowNum();
        InternalExcelUtil.assertTrue(rowCount <= rowNumLimit, String.format("读取的Excel行数(%s)不能超过%s.", rowCount, rowNumLimit));
        return InternalExcelUtil.readSheetData(sheet, rowCount, rowBeginIndex, isAllowNull);
    }

    public static List<NameValue<String, List<List<Object>>>> readAllSheets(InputStream ins, int rowBeginIndex, int rowNumLimit, boolean isAllowNull) {
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(ins);
        } catch (Throwable e) {
            InternalExcelUtil.wrapAndRethrowException(e, "Fail to read excel.");
        } finally {
            StreamUtils.close(ins);
        }

        int sheetCount = workbook.getNumberOfSheets();
        if (sheetCount <= 0) {
            return null;
        }
        List<NameValue<String, List<List<Object>>>> nameValues = new ArrayList<>(sheetCount);
        for (int sheetIndex = 0; sheetIndex < sheetCount; sheetIndex++) {
            String sheetName = getString(workbook.getSheetName(sheetIndex));
            if (StringUtils.isBlank(sheetName)) {
                continue;
            }
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            if (sheet == null) {
                continue;
            }
            int rowCount = sheet.getLastRowNum();
            InternalExcelUtil.assertTrue(rowCount <= rowNumLimit, String.format("读取的Excel行数(%s)不能超过%s.", rowCount, rowNumLimit));
            List<List<Object>> dataList = InternalExcelUtil.readSheetData(sheet, rowCount, rowBeginIndex, isAllowNull);

            nameValues.add(new NameValue<>(sheetName, dataList));
        }

        return nameValues;
    }


    /**
     * 输出Excel
     *
     * @param sheetName Sheet name
     * @param headers   Excel 头
     * @param dataList  数据列表
     * @return
     */
    public static ByteArrayOutputStream write(String sheetName, String[] headers, List<List<Object>> dataList) {
        InternalExcelUtil.assertTrue(InternalExcelUtil.isNotEmpty(headers), "Header list is empty.");

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);
        //构建文件头
        InternalExcelUtil.buildHeaderRow(workbook, sheet, headers);
        //构建数据行
        InternalExcelUtil.buildDataRows(sheet, headers.length, dataList);

        //输出
        ByteArrayOutputStream ous = null;
        try {
            ous = new ByteArrayOutputStream();
            workbook.write(ous);
        } catch (IOException e) {
            InternalExcelUtil.wrapAndRethrowException(e, "Fail to write excel.");
        }
        return ous;
    }

    public static String getStringExcelCellValue(Object cellValue) {
        if (cellValue == null) {
            return null;
        }
        String safedString = String.valueOf(cellValue).trim();
        safedString = safedString.trim().replaceAll(" ", "").replaceAll("\\n", "");
        if (StringUtils.isBlank(safedString)) {
            return null;
        }
        return safedString;
    }

    public static String getString(Object value) {
        return getStringExcelCellValue(value);
    }

    public static Integer getInteger(Object value) {
        String valueStr = getString(value);
        return StringUtils.isBlank(valueStr) ? null : Integer.valueOf(valueStr);
    }

    public static Boolean getBoolean(Object value) {
        String valueStr = getString(value);
        return StringUtils.isBlank(valueStr) ? null : "是".equalsIgnoreCase(valueStr);
    }
}
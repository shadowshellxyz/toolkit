package net.shadowdefender.toolkit.officesuite.excel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import net.shadowdefender.toolkit.standard.exception.AppException;

/**
 * InternalExcelUtil
 *
 * @author shadow
 */
class InternalExcelUtil {

    /**
     * 获取字符串列值
     *
     * @param cell Cell
     * @return
     */
    public static String getValueAsString(Cell cell) {
        String cellValue = "";
        if (null == cell) {
            return cellValue;
        }

        CellType cellType = cell.getCellType();
        switch (cellType) {
            case NUMERIC:
                Double doubleValue = cell.getNumericCellValue();
                String str = String.valueOf(doubleValue);
                if (str.contains("E")) {
                    Double subStr = Double.parseDouble(str);
                    str = new DecimalFormat("#").format(subStr);
                }
                if (str.contains(".0")) {
                    str = str.replace(".0", "");
                }
                cellValue = str;
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case BOOLEAN:
                Boolean booleanValue = cell.getBooleanCellValue();
                cellValue = booleanValue.toString();
                break;
            case BLANK:
                break;
            case FORMULA:
                cellValue = cell.getCellFormula();
                break;
            case _NONE:
                break;
            default:
                assertTrue(false, "未知Cell类型");
        }
        return cellValue.trim();
    }

    public static Font buildFont(Workbook workbook) {
        Font font = workbook.createFont();
        font.setFontName("黑体");
        font.setBold(true);
        font.setFontHeightInPoints((short) 10);

        return font;
    }

    public static CellStyle buildCellStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setFont(InternalExcelUtil.buildFont(workbook));
        return cellStyle;
    }

    public static void buildHeaderRow(Workbook workbook, Sheet sheet, String[] headers) {
        Row row = sheet.createRow(0);
        CellStyle style = InternalExcelUtil.buildCellStyle(workbook);

        for (int index = 0; index < headers.length; index++) {
            Cell cell = row.createCell(index);
            cell.setCellValue(headers[index]);
            cell.setCellStyle(style);
        }
    }

    public static void buildDataRows(Sheet sheet, int cellCount, List<List<Object>> dataList) {
        if (isEmpty(dataList)) {
            return;
        }

        for (int rowIndex = 1; rowIndex <= dataList.size(); rowIndex++) {
            Row row = sheet.createRow(rowIndex);
            List<Object> rowData = dataList.get(rowIndex - 1);
            if (isEmpty(rowData)) {
                continue;
            }
            for (int cellIndex = 0; cellIndex < cellCount; cellIndex++) {
                if (cellIndex + 1 > cellCount) {
                    continue;
                }
                String cellValue = String.valueOf(rowData.get(cellIndex));
                row.createCell(cellIndex).setCellValue(cellValue);
            }
        }
    }

    public static List<List<Object>> readSheetData(Sheet sheet, int rowCount, int rowBeginIndex, boolean isAllowNull) {
        int cellCount = rowBeginIndex < rowCount ? sheet.getRow(rowBeginIndex).getLastCellNum() : 0;
        if (cellCount <= 0) {
            return null;
        }

        List<List<Object>> sheetData = new ArrayList<>(rowCount);
        for (int rowIndex = rowBeginIndex + 1; rowIndex <= rowCount; rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                continue;
            }
            List<Object> rowData = new ArrayList<>(cellCount);
            for (int cellIndex = 0; cellIndex < cellCount; cellIndex++) {
                Cell cell = row.getCell(cellIndex);
                String value = getValueAsString(cell);
                boolean isAddToRow = (isAllowNull || isNotBlank(value));
                if (isAddToRow) {
                    rowData.add(value);
                }
            }
            boolean isAddToSheet = (isAllowNull || cellCount == rowData.size());
            if (isAddToSheet) {
                sheetData.add(rowData);
            }
        }
        return sheetData;
    }

    public static boolean isNotBlank(String string) {
        return string != null && !string.trim().equals("");
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <E> boolean isNotEmpty(E[] array) {
        return array != null && array.length > 0;
    }

    public static void assertTrue(boolean expression, String message) {
        if (!expression) {
            throw new AppException(message);
        }
    }

    public static void wrapAndRethrowException(Throwable e, String customizedMessage) {
        throw new AppException(customizedMessage, e);
    }
}
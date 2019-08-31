package org.walkerljl.armor.excel.console.monitor;

import java.util.Date;

import org.walkerljl.armor.excel.ScriptBuilder;
import org.walkerljl.armor.excel.console.AbstractConsoleScriptBuilder;
import org.walkerljl.toolkit.lang.datetime.DateUtils;

/**
 *
 * @author xingxun
 */
public class SaveMonitorDataScriptBuilder extends AbstractConsoleScriptBuilder implements ScriptBuilder {

    public SaveMonitorDataScriptBuilder(String sourceFilePath, String scriptFileOutputPath) {
        super(sourceFilePath, scriptFileOutputPath);
    }

    public SaveMonitorDataScriptBuilder(String sourceFilePath, String scriptFileOutputPath, int rowEndIndex) {
        super(sourceFilePath, scriptFileOutputPath, rowEndIndex);
    }

    public SaveMonitorDataScriptBuilder(String sourceFilePath, String scriptFileOutputPath, int sheetIndex, int rowIndex,
                                        int rowEndIndex) {
        super(sourceFilePath, scriptFileOutputPath, sheetIndex, rowIndex, rowEndIndex);
    }

    @Override
    protected String getCommand() {
        return "monitor.data.save";
    }

    @Override
    protected String getParamTemplate() {
        String template = "{\"bizCode\":\"%s\",\"objId\":\"%s\","
                + "\"time\":\"%s\",\"value\":\"%s\",\"remark\":\"\",\"extInfo\":\"\",\"status\":\"10\",\"creator\":\"junlin.ljl\","
                + "\"createdTime\":\"%s\",\"modifiedTime\":\"%s\",\"modifier\":\"junlin.ljl\"}";

        String dateStr = DateUtils.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss");

        return String.format(template, "%s", "%s", "%s", "%s", dateStr, dateStr);
    }

    @Override
    protected int[] getParamDataIndexes() {
        return new int[]{0, 1, 2, 3};
    }

    @Override
    protected String getParamSeparator() {
        return ",";
    }
}
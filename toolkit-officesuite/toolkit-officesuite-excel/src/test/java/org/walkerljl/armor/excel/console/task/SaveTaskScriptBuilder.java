package org.walkerljl.armor.excel.console.task;

import java.util.Date;

import org.walkerljl.armor.excel.ScriptBuilder;
import org.walkerljl.armor.excel.console.AbstractConsoleScriptBuilder;
import org.walkerljl.toolkit.lang.datetime.DateUtils;

/**
 *
 * @author xingxun
 */
public class SaveTaskScriptBuilder extends AbstractConsoleScriptBuilder implements ScriptBuilder {

    public SaveTaskScriptBuilder(String sourceFilePath, String scriptFileOutputPath) {
        super(sourceFilePath, scriptFileOutputPath);
    }

    public SaveTaskScriptBuilder(String sourceFilePath, String scriptFileOutputPath, int rowEndIndex) {
        super(sourceFilePath, scriptFileOutputPath, rowEndIndex);
    }

    public SaveTaskScriptBuilder(String sourceFilePath, String scriptFileOutputPath, int sheetIndex, int rowIndex, int rowEndIndex) {
        super(sourceFilePath, scriptFileOutputPath, sheetIndex, rowIndex, rowEndIndex);
    }

    @Override
    protected String getCommand() {
        return "task.save";
    }

    @Override
    protected String getParamTemplate() {
        String template = "{\"bizCode\":\"%s\",\"bizId\":\"%s@@2018-11-30\","
                + "\"handlerId\":\"AlarmAnalyzer\",\"priority\":5,\"attempts\":0,\"maxAttempts\":10,\"retryRule\":\"10\","
                + "\"lastRetryTime\":\"%s\",\"nextRetryTime\":\"%s\",\"remark\":\"\",\"extInfo\":\"\","
                + "\"status\":\"30\",\"createdTime\":\"%s\",\"creator\":\"junlin.ljl\",\"modifiedTime\":\"%s\",\"modifier\":\"junlin.ljl\"}";


        String dateStr = DateUtils.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss");
        return String.format(template, "%s", "%s", dateStr, dateStr, dateStr, dateStr);
    }

    @Override
    protected int[] getParamDataIndexes() {
        return new int[]{0, 2};
    }

    @Override
    protected String getParamSeparator() {
        return ",";
    }
}
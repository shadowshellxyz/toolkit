package org.walkerljl.armor.excel.console.monitor.alarm.rule;

import java.util.Date;

import org.walkerljl.armor.excel.ScriptBuilder;
import org.walkerljl.armor.excel.console.AbstractConsoleScriptBuilder;
import org.walkerljl.toolkit.lang.datetime.DateUtils;

/**
 *
 * @author xingxun
 */
public class SaveAlarmRule2ScriptBuilder extends AbstractConsoleScriptBuilder implements ScriptBuilder {

    public SaveAlarmRule2ScriptBuilder(String sourceFilePath, String scriptFileOutputPath) {
        super(sourceFilePath, scriptFileOutputPath);
    }

    public SaveAlarmRule2ScriptBuilder(String sourceFilePath, String scriptFileOutputPath, int rowEndIndex) {
        super(sourceFilePath, scriptFileOutputPath, rowEndIndex);
    }

    public SaveAlarmRule2ScriptBuilder(String sourceFilePath, String scriptFileOutputPath, int sheetIndex, int rowIndex, int rowEndIndex) {
        super(sourceFilePath, scriptFileOutputPath, sheetIndex, rowIndex, rowEndIndex);
    }

    @Override
    protected String getCommand() {
        return "alarm.rule.save";
    }

    @Override
    protected String getParamTemplate() {
        String template = "{\"bizCode\":\"%s\",\"objId\":\"%s\",\"type\":\"1000\","
                + "\"expression\":\"2,decline,0.1\",\"channels\":\"[\\\"mrchishub\\\"]\",\"remark\":\"连续两日环比下降超过0.1预警\","
                + "\"extInfo\":\"{\\\"mrchishub.bizcode\\\":\\\"00020033\\\"}\",\"status\":\"10\",\"createdTime\":\"%s\",\"creator\":\"junlin.ljl\",\"modifiedTime\":\"%s\",\"modifier\":\"junlin.ljl\"}";

        String dateStr = DateUtils.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss");
        return String.format(template, "%s", "%s", dateStr, dateStr);
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
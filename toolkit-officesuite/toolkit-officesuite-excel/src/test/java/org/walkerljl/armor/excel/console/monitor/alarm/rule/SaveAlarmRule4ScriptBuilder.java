package org.walkerljl.armor.excel.console.monitor.alarm.rule;

import java.util.Date;

import org.walkerljl.armor.excel.ScriptBuilder;
import org.walkerljl.armor.excel.console.AbstractConsoleScriptBuilder;
import org.walkerljl.toolkit.lang.datetime.DateUtils;

/**
 *
 * @author xingxun
 */
public class SaveAlarmRule4ScriptBuilder extends AbstractConsoleScriptBuilder implements ScriptBuilder {

    public SaveAlarmRule4ScriptBuilder(String sourceFilePath, String scriptFileOutputPath) {
        super(sourceFilePath, scriptFileOutputPath);
    }

    public SaveAlarmRule4ScriptBuilder(String sourceFilePath, String scriptFileOutputPath, int rowEndIndex) {
        super(sourceFilePath, scriptFileOutputPath, rowEndIndex);
    }

    public SaveAlarmRule4ScriptBuilder(String sourceFilePath, String scriptFileOutputPath, int sheetIndex, int rowIndex, int rowEndIndex) {
        super(sourceFilePath, scriptFileOutputPath, sheetIndex, rowIndex, rowEndIndex);
    }

    @Override
    protected String getCommand() {
        return "alarm.rule.save";
    }

    @Override
    protected String getParamTemplate() {
        String template = "{\"bizCode\":\"%s\",\"objId\":\"%s\",\"type\":\"1001\","
                + "\"expression\":\"30\",\"channels\":\"[\\\"mrchishub\\\"]\",\"remark\":\"当日达近30日峰值恭喜\","
                + "\"extInfo\":\"{\\\"mrchishub.bizcode\\\":\\\"00020035\\\"}\",\"status\":\"10\",\"createdTime\":\"%s\",\"creator\":\"junlin.ljl\",\"modifiedTime\":\"%s\",\"modifier\":\"junlin.ljl\"}";

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
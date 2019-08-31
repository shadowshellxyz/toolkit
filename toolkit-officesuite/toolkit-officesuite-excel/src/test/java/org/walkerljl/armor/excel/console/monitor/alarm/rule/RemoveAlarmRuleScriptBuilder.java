package org.walkerljl.armor.excel.console.monitor.alarm.rule;

import org.walkerljl.armor.excel.ScriptBuilder;
import org.walkerljl.armor.excel.console.AbstractConsoleScriptBuilder;

/**
 *
 * @author xingxun
 */
public class RemoveAlarmRuleScriptBuilder extends AbstractConsoleScriptBuilder implements ScriptBuilder {

    public RemoveAlarmRuleScriptBuilder(String sourceFilePath, String scriptFileOutputPath) {
        super(sourceFilePath, scriptFileOutputPath);
    }

    public RemoveAlarmRuleScriptBuilder(String sourceFilePath, String scriptFileOutputPath, int rowEndIndex) {
        super(sourceFilePath, scriptFileOutputPath, rowEndIndex);
    }

    public RemoveAlarmRuleScriptBuilder(String sourceFilePath, String scriptFileOutputPath, int sheetIndex, int rowIndex, int rowEndIndex) {
        super(sourceFilePath, scriptFileOutputPath, sheetIndex, rowIndex, rowEndIndex);
    }

    @Override
    protected String getCommand() {
        return "alarm.rule.remove";
    }

    @Override
    protected String getParamTemplate() {
        return "{\"bizCode\":\"%s\",\"objId\":\"%s\",\"id\":%s}";
    }

    @Override
    protected int[] getParamDataIndexes() {
        return new int[]{3, 4, 0};
    }

    @Override
    protected String getParamSeparator() {
        return ",";
    }

}
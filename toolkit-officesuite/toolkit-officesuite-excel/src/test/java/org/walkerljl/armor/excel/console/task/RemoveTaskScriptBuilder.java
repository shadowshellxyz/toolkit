package org.walkerljl.armor.excel.console.task;

import org.walkerljl.armor.excel.ScriptBuilder;
import org.walkerljl.armor.excel.console.AbstractConsoleScriptBuilder;

/**
 *
 * @author xingxun
 */
public class RemoveTaskScriptBuilder extends AbstractConsoleScriptBuilder implements ScriptBuilder {

    public RemoveTaskScriptBuilder(String sourceFilePath, String scriptFileOutputPath) {
        super(sourceFilePath, scriptFileOutputPath);
    }

    public RemoveTaskScriptBuilder(String sourceFilePath, String scriptFileOutputPath, int rowEndIndex) {
        super(sourceFilePath, scriptFileOutputPath, rowEndIndex);
    }

    public RemoveTaskScriptBuilder(String sourceFilePath, String scriptFileOutputPath, int sheetIndex, int rowIndex, int rowEndIndex) {
        super(sourceFilePath, scriptFileOutputPath, sheetIndex, rowIndex, rowEndIndex);
    }

    @Override
    protected String getCommand() {
        return "task.remove";
    }

    @Override
    protected String getParamTemplate() {
        return "{\"bizCode\":\"%s\",\"bizId\":\"%s\",\"id\":%s}";
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
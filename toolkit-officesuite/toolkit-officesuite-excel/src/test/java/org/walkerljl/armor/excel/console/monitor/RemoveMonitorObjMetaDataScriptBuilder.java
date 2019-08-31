package org.walkerljl.armor.excel.console.monitor;

import org.walkerljl.armor.excel.ScriptBuilder;
import org.walkerljl.armor.excel.console.AbstractConsoleScriptBuilder;

/**
 *
 * @author xingxun
 */
public class RemoveMonitorObjMetaDataScriptBuilder extends AbstractConsoleScriptBuilder implements ScriptBuilder {

    public RemoveMonitorObjMetaDataScriptBuilder(String sourceFilePath, String scriptFileOutputPath) {
        super(sourceFilePath, scriptFileOutputPath);
    }

    public RemoveMonitorObjMetaDataScriptBuilder(String sourceFilePath, String scriptFileOutputPath, int rowEndIndex) {
        super(sourceFilePath, scriptFileOutputPath, rowEndIndex);
    }

    public RemoveMonitorObjMetaDataScriptBuilder(String sourceFilePath, String scriptFileOutputPath, int sheetIndex, int rowIndex,
                                                 int rowEndIndex) {
        super(sourceFilePath, scriptFileOutputPath, sheetIndex, rowIndex, rowEndIndex);
    }

    @Override
    protected String getCommand() {
        return "monitor.obj.meta.data.remove";
    }

    @Override
    protected String getParamTemplate() {
        return "{\"bizCode\":\"%s\",\"objId\":\"%s\",\"id\":%s}";
    }

    @Override
    protected int[] getParamDataIndexes() {
        return new int[]{3, 5, 0};
    }

    @Override
    protected String getParamSeparator() {
        return ",";
    }

}
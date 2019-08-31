package org.walkerljl.armor.excel.console.comment;

import org.walkerljl.armor.excel.ScriptBuilder;
import org.walkerljl.armor.excel.console.AbstractConsoleScriptBuilder;

/**
 *
 * @author xingxun
 */
public class RemoveCommentScriptBuilder extends AbstractConsoleScriptBuilder implements ScriptBuilder {

    public RemoveCommentScriptBuilder(String sourceFilePath, String scriptFileOutputPath) {
        super(sourceFilePath, scriptFileOutputPath);
    }

    public RemoveCommentScriptBuilder(String sourceFilePath, String scriptFileOutputPath, int rowEndIndex) {
        super(sourceFilePath, scriptFileOutputPath, rowEndIndex);
    }

    public RemoveCommentScriptBuilder(String sourceFilePath, String scriptFileOutputPath, int sheetIndex, int rowIndex, int rowEndIndex) {
        super(sourceFilePath, scriptFileOutputPath, sheetIndex, rowIndex, rowEndIndex);
    }

    @Override
    protected String getCommand() {
        return "comment.remove";
    }

    @Override
    protected String getParamTemplate() {
        return "\"%s\"";
    }

    @Override
    protected int[] getParamDataIndexes() {
        return new int[]{0};
    }

    @Override
    protected String getParamSeparator() {
        return ",";
    }

}
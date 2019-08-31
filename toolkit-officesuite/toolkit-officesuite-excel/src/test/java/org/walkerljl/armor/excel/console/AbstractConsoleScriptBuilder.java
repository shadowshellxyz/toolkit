package org.walkerljl.armor.excel.console;

import java.util.List;

import org.walkerljl.armor.excel.AbstractScriptBuilder;

/**
 * AbstractConsoleScriptBuilder
 *
 * @author xingxun
 */
public abstract class AbstractConsoleScriptBuilder extends AbstractScriptBuilder {

    private int rowEndIndex;

    public AbstractConsoleScriptBuilder(String sourceFilePath, String scriptFileOutputPath) {
        this(sourceFilePath, scriptFileOutputPath, 0, 0, Integer.MAX_VALUE);
    }

    public AbstractConsoleScriptBuilder(String sourceFilePath, String scriptFileOutputPath, int rowEndIndex) {
        this(sourceFilePath, scriptFileOutputPath, 0, 0, rowEndIndex);
    }

    public AbstractConsoleScriptBuilder(String sourceFilePath, String scriptFileOutputPath, int sheetIndex, int rowIndex, int rowEndIndex) {
        super(sourceFilePath, scriptFileOutputPath, sheetIndex, rowIndex);
        this.rowEndIndex = rowEndIndex;
    }

    @Override
    public String build0(List<List<Object>> dataSource) {
        String consoleTemplate = "{\"command\":\"%s\",\"param\":[%s]}";
        String consoleScript = String.format(consoleTemplate, getCommand(), buildConsole0(dataSource));
        return consoleScript;
    }

    /**
     * buildConsole0
     *
     * @param dataSource
     * @return
     */
    private String buildConsole0(List<List<Object>> dataSource) {
        if (dataSource == null) {
            return "";
        }

        String template = getParamTemplate();
        int[] dataIndexes = getParamDataIndexes();
        String paramSeparator = getParamSeparator();

        StringBuilder scriptItems = new StringBuilder();
        for (int i = 0; i < dataSource.size(); i++) {
            List<Object> row = dataSource.get(i);
            if (row == null) {
                continue;
            }

            String[] datas = new String[dataIndexes.length];
            String scriptItem = null;
            for (int j = 0; j < dataIndexes.length; j++) {
                datas[j] = String.valueOf(row.get(dataIndexes[j]));
                scriptItem = String.format(template, datas);
            }
            if (i != 0) {
                scriptItems.append(paramSeparator);
            }
            scriptItems.append(scriptItem);

            if (i >= rowEndIndex) {
                break;
            }
        }

        return scriptItems.toString();
    }

    /**
     * 获取命令
     *
     * @return
     */
    protected abstract String getCommand();

    /**
     * 获取参数模版
     *
     * @return
     */
    protected abstract String getParamTemplate();

    /**
     * 获取参数数据索引列表
     *
     * @return
     */
    protected abstract int[] getParamDataIndexes();

    /**
     * 获取参数之间的分隔符
     *
     * @return
     */
    protected abstract String getParamSeparator();
}
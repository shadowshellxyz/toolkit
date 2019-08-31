
package org.walkerljl.armor.excel.console.monitor;

import java.util.List;

import org.walkerljl.armor.excel.AbstractScriptBuilder;
import org.walkerljl.armor.excel.ScriptBuilder;

/**
 *
 * @author xingxun

 */
public class IdxCodeInfoScriptBuilder extends AbstractScriptBuilder implements ScriptBuilder {

    public IdxCodeInfoScriptBuilder(String sourceFilePath, String scriptFileOutputPath) {
        super(sourceFilePath, scriptFileOutputPath);
    }

    public IdxCodeInfoScriptBuilder(String sourceFilePath, String scriptFileOutputPath, int sheetIndex, int rowBeginIndex) {
        super(sourceFilePath, scriptFileOutputPath, sheetIndex, rowBeginIndex);
    }

    @Override
    public String build0(List<List<Object>> dataSource) {
        StringBuilder sb = new StringBuilder();

        for (List<Object> row : dataSource) {
            String item = String.format("%s %s", row.get(2), row.get(3));
            sb.append(item);
            System.out.println(item);
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        String sourceFilePathPrefix = "/Users/walkerljl/workbench/alipay/note/iEye/";
        String scriptFileOutputPathPrefix = "/Users/walkerljl/Desktop/";
        new IdxCodeInfoScriptBuilder(sourceFilePathPrefix + "TaskInfo.xlsx", scriptFileOutputPathPrefix + "draft.txt")
                .start().run().stop();
    }
}
package org.walkerljl.armor.excel.console.monitor;

import java.util.Date;

import org.walkerljl.armor.excel.ScriptBuilder;
import org.walkerljl.armor.excel.console.AbstractConsoleScriptBuilder;
import org.walkerljl.toolkit.lang.datetime.DateUtils;

/**
 *
 * @author xingxun
 */
public class SaveMonitorObjMetaDataScriptBuilder extends AbstractConsoleScriptBuilder implements ScriptBuilder {

    public SaveMonitorObjMetaDataScriptBuilder(String sourceFilePath, String scriptFileOutputPath) {
        super(sourceFilePath, scriptFileOutputPath);
    }

    public SaveMonitorObjMetaDataScriptBuilder(String sourceFilePath, String scriptFileOutputPath, int rowEndIndex) {
        super(sourceFilePath, scriptFileOutputPath, rowEndIndex);
    }

    public SaveMonitorObjMetaDataScriptBuilder(String sourceFilePath, String scriptFileOutputPath, int sheetIndex, int rowIndex,
                                               int rowEndIndex) {
        super(sourceFilePath, scriptFileOutputPath, sheetIndex, rowIndex, rowEndIndex);
    }

    @Override
    protected String getCommand() {
        return "monitor.obj.meta.data.save";
    }

    @Override
    protected String getParamTemplate() {
        String template = "{\"bizCode\":\"%s\",\"bizName\":\"%s\",\"objId\":\"%s\","
                + "\"objName\":\"%s\",\"alarmReceivers\":\"\",\"bizOwner\":\"<junlin.ljl>行寻\",\"remark\":\"\","
                + "\"extInfo\":\"{\\\"mrchishub.detail.relative.url\\\":\\\"/rabbit/component/mobile/v2/layout"
                + ".htm?scene=ieye_kpi_detail&bizCode=%s&dimCode=%s&idxCode=%s\\\"}\",\"status\":\"10\","
                + "\"createdTime\":\"%s\",\"creator\":\"junlin.ljl\",\"modifiedTime\":\"%s\","
                + "\"modifier\":\"junlin.ljl\"}";

        String dateStr = DateUtils.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss");

        return String.format(template, "%s", "%s", "%s", "%s", "%s", "%s", "%s", dateStr, dateStr);
    }

    @Override
    protected int[] getParamDataIndexes() {
        return new int[]{0, 1, 2, 3, 4, 5, 6};
    }

    @Override
    protected String getParamSeparator() {
        return ",";
    }
}
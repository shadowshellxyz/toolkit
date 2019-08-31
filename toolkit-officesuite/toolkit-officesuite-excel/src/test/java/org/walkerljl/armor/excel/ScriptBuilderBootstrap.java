package org.walkerljl.armor.excel;

import org.junit.Test;
import org.walkerljl.armor.excel.console.comment.RemoveCommentScriptBuilder;
import org.walkerljl.armor.excel.console.monitor.RemoveMonitorDataScriptBuilder;
import org.walkerljl.armor.excel.console.monitor.RemoveMonitorObjMetaDataScriptBuilder;
import org.walkerljl.armor.excel.console.monitor.SaveMonitorDataScriptBuilder;
import org.walkerljl.armor.excel.console.monitor.SaveMonitorObjMetaDataScriptBuilder;
import org.walkerljl.armor.excel.console.monitor.alarm.rule.RemoveAlarmRuleScriptBuilder;
import org.walkerljl.armor.excel.console.monitor.alarm.rule.SaveAlarmRule1ScriptBuilder;
import org.walkerljl.armor.excel.console.monitor.alarm.rule.SaveAlarmRule2ScriptBuilder;
import org.walkerljl.armor.excel.console.monitor.alarm.rule.SaveAlarmRule3ScriptBuilder;
import org.walkerljl.armor.excel.console.monitor.alarm.rule.SaveAlarmRule4ScriptBuilder;
import org.walkerljl.armor.excel.console.monitor.alarm.rule.SaveAlarmRule5ScriptBuilder;
import org.walkerljl.armor.excel.console.task.RemoveTaskScriptBuilder;
import org.walkerljl.armor.excel.console.task.SaveTaskScriptBuilder;

/**
 * ScriptBuilderBootstrap
 *
 * @author xingxun
 */
public class ScriptBuilderBootstrap {

    private String scriptFileOutputPathPrefix = "/Users/walkerljl/workbench/alipay/note/iEye/console/";
    private String sourceFilePathPrefix = "/Users/walkerljl/workbench/alipay/note/iEye/";

    @Test
    public void run() {

        new RemoveMonitorObjMetaDataScriptBuilder("/Users/walkerljl/Downloads/执行结果-1 (53).xlsx",
                getScriptFileOutputPath("monitor/monitor.obj.meta.data.remove.txt")).start().run();
        new SaveMonitorObjMetaDataScriptBuilder(getSourceFilePath("TaskInfo.xlsx"),
                getScriptFileOutputPath("monitor/monitor.obj.meta.data.save.txt")).start().run();

        new RemoveAlarmRuleScriptBuilder("/Users/walkerljl/Downloads/执行结果-1 (56).xlsx",
                getScriptFileOutputPath("monitor/alarm/rule/alarm.rule.remove.txt")).start().run();

        new SaveAlarmRule1ScriptBuilder(getSourceFilePath("TaskInfo.xlsx"),
                getScriptFileOutputPath("monitor/alarm/rule/alarm.rule1.save.txt"),
                0, 0, 7).start().run();
        new SaveAlarmRule2ScriptBuilder(getSourceFilePath("TaskInfo.xlsx"),
                getScriptFileOutputPath("monitor/alarm/rule/alarm.rule2.save.txt"),
                0, 0, 7).start().run();
        new SaveAlarmRule3ScriptBuilder(getSourceFilePath("TaskInfo.xlsx"),
                getScriptFileOutputPath("monitor/alarm/rule/alarm.rule3.save.txt"),
                0, 0, 7).start().run();
        new SaveAlarmRule4ScriptBuilder(getSourceFilePath("TaskInfo.xlsx"),
                getScriptFileOutputPath("monitor/alarm/rule/alarm.rule4.save.txt"),
                    0, 0, 7).start().run();
        new SaveAlarmRule5ScriptBuilder(getSourceFilePath("TaskInfo.xlsx"),
                getScriptFileOutputPath("monitor/alarm/rule/alarm.rule5.save.txt"),
                    0, 8, Integer.MAX_VALUE).start().run();


        new RemoveMonitorDataScriptBuilder("/Users/walkerljl/Downloads/执行结果-1 (60).xlsx",
                getScriptFileOutputPath("monitor/monitor.data.remove.txt")).start().run();

        new SaveMonitorDataScriptBuilder(getSourceFilePath("TaskInfo.xlsx"),
                getScriptFileOutputPath("monitor/monitor.data.save.txt"),
                    1, 0, Integer.MAX_VALUE).start().run();

        new RemoveTaskScriptBuilder("/Users/walkerljl/Downloads/执行结果-1 (61).xlsx",
                getScriptFileOutputPath("task/task.remove.txt")).start().run();
        new SaveTaskScriptBuilder(getSourceFilePath("TaskInfo.xlsx"),
                getScriptFileOutputPath("task/task.save.txt"),0, 0, 0).start().run();

        new RemoveCommentScriptBuilder("/Users/walkerljl/Downloads/执行结果-1 (62).xlsx",
                getScriptFileOutputPath("comment/comment.remove.txt")).start().run();
    }

    private String getScriptFileOutputPath(String outputScriptFileName) {
        return String.format("%s%s", scriptFileOutputPathPrefix, outputScriptFileName);
    }

    private String getSourceFilePath(String sourceFileName) {
        return String.format("%s%s", sourceFilePathPrefix, sourceFileName);
    }
}
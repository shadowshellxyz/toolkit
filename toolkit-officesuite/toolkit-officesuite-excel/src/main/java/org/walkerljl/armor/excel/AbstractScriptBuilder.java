package org.walkerljl.armor.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import org.walkerljl.toolkit.lang.io.StreamUtils;
import org.walkerljl.toolkit.standard.machine.abstracts.AbstractMachine;
import org.walkerljl.toolkit.standard.machine.exception.CannotStartMachineException;
import org.walkerljl.toolkit.standard.machine.exception.CannotStopMachineException;
import org.walkerljl.toolkit.standard.machine.exception.MachineRunException;

/**
 * AbstractScriptBuilder
 *
 * @author xingxun
 */
public abstract class AbstractScriptBuilder extends AbstractMachine implements ScriptBuilder {

    private String sourceFilePath;
    private String scriptFileOutputPath;
    private int sheetIndex;
    private int rowBeginIndex;

    public AbstractScriptBuilder(String sourceFilePath, String scriptFileOutputPath) {
        this(sourceFilePath, scriptFileOutputPath, 0, 0);
    }

    public AbstractScriptBuilder(String sourceFilePath, String scriptFileOutputPath, int sheetIndex, int rowBeginIndex) {
        this.sourceFilePath = sourceFilePath;
        this.scriptFileOutputPath = scriptFileOutputPath;
        this.sheetIndex = sheetIndex;
        this.rowBeginIndex = rowBeginIndex;
    }

    @Override
    public void build(String sourceFilePath, String scriptFileOutputPath) {
        InputStream ins = null;
        List<List<Object>> dataList = null;
        try {
            ins = new FileInputStream(sourceFilePath);
            dataList = ExcelUtils.read(ins, sheetIndex, rowBeginIndex, Integer.MAX_VALUE, true);
        } catch (FileNotFoundException e) {
           throw new RuntimeException(e);
        } finally {
            StreamUtils.close(ins);
        }

        String scriptText = build0(dataList);

        FileOutputStream ous = null;
        try {
            ous = new FileOutputStream(scriptFileOutputPath);
            ous.write(scriptText.getBytes());
            ous.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            StreamUtils.close(ous);
        }
    }

    @Override
    public void processStart() throws CannotStartMachineException {

    }

    @Override
    protected void processRun() throws MachineRunException {
        build(this.sourceFilePath, this.scriptFileOutputPath);
    }

    @Override
    public void processStop() throws CannotStopMachineException {

    }

    /**
     * 脚本构建
     *
     * @param dataSource 数据源
     * @return
     */
    public abstract String build0(List<List<Object>> dataSource);
}
package org.walkerljl.armor.excel;

import org.walkerljl.toolkit.standard.machine.Machine;

/**
 * 脚本构建器
 *
 * @author xingxun
 */
public interface ScriptBuilder extends Machine {

    /**
     * 构建脚本
     *
     * @param sourceFilePath 源文件露肩
     * @param scriptFileOutputPath 脚本文件输出路径
     */
    void build(String sourceFilePath, String scriptFileOutputPath);
}
package org.walkerljl.toolkit.standard.machine;

import org.walkerljl.toolkit.standard.machine.exception.CannotStartMachineException;
import org.walkerljl.toolkit.standard.machine.exception.CannotStopMachineException;
import org.walkerljl.toolkit.standard.machine.exception.MachineException;
import org.walkerljl.toolkit.standard.resource.Resource;

/**
 * 机器
 *
 * @author xingxun
 */
public interface Machine extends Resource {

    /**
     * 启动
     *
     * @return
     * @throws CannotStartMachineException
     */
    Machine start() throws CannotStartMachineException;

    /**
     * 启动成功时的回调函数
     *
     * @throws MachineException
     */
    void callbackOnSuccessToStart() throws MachineException;

    /**
     * 启动失败时的回调函数
     *
     * @throws MachineException
     */
    void callbackOnFailureToStart() throws MachineException;

    /**
     * 运行
     *
     * @return
     * @throws MachineException
     */
    Machine run() throws MachineException;

    /**
     * 停止
     *
     * @return
     * @throws CannotStopMachineException
     */
    Machine stop() throws CannotStopMachineException;

    /**
     * 停止成功时回调
     *
     * @throws MachineException
     */
    void callbackOnSuccessToStop() throws MachineException;

    /**
     * 停止失败时回调
     *
     * @throws MachineException
     */
    void callbackOnFailureToStop() throws MachineException;

    /**
     * 重启，先停止，然后启动
     *
     * @return
     * @throws CannotStartMachineException
     * @throws CannotStopMachineException
     */
    Machine restart() throws CannotStartMachineException, CannotStopMachineException;

    /**
     * 判断是否正在运行中
     *
     * @return true：运行中，false：未运行
     */
    boolean isRunning();
}

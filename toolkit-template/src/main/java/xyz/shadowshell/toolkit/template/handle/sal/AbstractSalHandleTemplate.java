package xyz.shadowshell.toolkit.template.handle.sal;

import xyz.shadowshell.toolkit.template.handle.AbstractHandleTemplate;
import xyz.shadowshell.toolkit.template.log.model.InvocationInfo;
import xyz.shadowshell.toolkit.template.log.Logger;
import xyz.shadowshell.toolkit.template.log.LoggerFactory;
import xyz.shadowshell.toolkit.template.log.util.LoggerUtil;

/**
 * 抽象的SAL处理模板
 *
 * @author shadow
 */
public abstract class AbstractSalHandleTemplate extends AbstractHandleTemplate {

    /**
     * 本地日志打印对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSalHandleTemplate.class);

    /**
     * 处理业务
     *
     * @param handler        处理器
     * @param invocationInfo 调用信息
     * @return
     */
    public <PARAM, RESULT> RESULT handle(InvocationInfo<PARAM, RESULT> invocationInfo, SalHandler<PARAM, RESULT> handler) {
        RESULT result = null;
        try {
            // 参数校验
            SalAssertUtil.assertParam(invocationInfo != null, "invocationInfo");
            SalAssertUtil.assertParam(handler != null, "handler");

            // 业务参数校验
            boolean isPassedCheckParams = handler.checkParams(invocationInfo.getParam());
            SalAssertUtil.assertTrue(isPassedCheckParams, SalErrorCode.INVALID_PARAM);

            //业务执行
            result = handler.handle(invocationInfo.getParam());

            //invocationInfo.markSuccess(result);
        } catch (Throwable e) {
            if (invocationInfo != null) {
                invocationInfo.markFailure(e);
            }
        } finally {
            try {
                doLog(invocationInfo);
            } catch (Throwable e) {
                LoggerUtil.error(LOGGER, e);
            }
            if (canRethrowException()) {
                assertInvocationInfo(invocationInfo);
            }
        }
        return result;
    }

    /**
     * 校验调用信息
     *
     * @param invocationInfo 调用信息
     * @param <PARAM>
     * @param <RESULT>
     */
    private <PARAM, RESULT> void assertInvocationInfo(InvocationInfo<PARAM, RESULT> invocationInfo) {
        SalAssertUtil.assertParam(invocationInfo != null, "invocationInfo");
        if (invocationInfo.isSuccess()) {
            return;
        }
        Throwable throwable = invocationInfo.getThrowable();
        if (throwable == null) {
            throw new AppSalException(invocationInfo.getTraceInfo());
        } else {
            if (throwable instanceof AppSalException) {
                throw new AppSalException(((AppSalException) throwable).getCode(), throwable.getMessage(), throwable);
            } else {
                throw new AppSalException(throwable.getMessage(), throwable);
            }
        }
    }
}
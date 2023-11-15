package xyz.shadowshell.toolkit.sirector;

/**
 * Sirector回调函数
 *
 * @author shadow
 */
public interface SirectorCallback<Event> {

    /**
     * 响应成功
     *
     * @param event 事件
     */
    void onSuccess(Event event);

    /**
     * 响应错误
     *
     * @param event     事件
     * @param throwable 异常
     */
    void onError(Event event, Throwable throwable);
}

package xyz.shadowshell.toolkit.sirector;

/**
 * Sirector事件处理器
 *
 * @author shadow
 */
public interface SirectorEventHandler<Event> {

    /**
     * 响应事件
     *
     * @param event 事件
     */
    void onEvent(Event event);
}

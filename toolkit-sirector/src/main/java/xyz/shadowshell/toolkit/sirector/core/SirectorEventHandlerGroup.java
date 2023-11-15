package xyz.shadowshell.toolkit.sirector.core;

import xyz.shadowshell.toolkit.sirector.SirectorEventHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Sirector事件处理器分组
 *
 * @author shadow
 */
public class SirectorEventHandlerGroup<Event> {

    /**
     * 事件Script
     */
    private final Script<Event> script;
    /**
     * 事件处理器列表
     */
    private List<SirectorEventHandler<Event>> eventHandlers;

    /**
     * 构造函数
     *
     * @param script        事件Script
     * @param eventHandlers 事件处理器列表
     */
    SirectorEventHandlerGroup(Script<Event> script, List<SirectorEventHandler<Event>> eventHandlers) {
        synchronized (script) {
            if (script.isReady()) {
                throw new IllegalStateException(
                        "script is ready, cannot be edit any more.");
            }
            this.script = script;
            this.eventHandlers = new ArrayList<>(eventHandlers.size());
            SirectorUtil.copy(eventHandlers, this.eventHandlers);

            for (SirectorEventHandler<Event> handler : eventHandlers) {
                script.addDependency(handler, null);
            }
        }
    }

    /**
     * 于指定处理器之后执行事件处理器列表
     *
     * @param eventHandlers 待执行事件处理器列表
     * @return
     */
    public SirectorEventHandlerGroup<Event> then(SirectorEventHandler<Event>... eventHandlers) {
        synchronized (script) {
            if (script.isReady()) {
                throw new IllegalStateException(
                        "script is ready, cannot be edit any more.");
            }
            for (SirectorEventHandler<Event> dependedEventHandler : this.eventHandlers) {
                for (SirectorEventHandler<Event> dependingEventHandler : eventHandlers) {
                    script.addDependency(dependedEventHandler, dependingEventHandler);
                }
            }

            for (SirectorEventHandler<Event> to : eventHandlers) {
                script.addDependency(to, null);
            }
            this.eventHandlers = new ArrayList<>(eventHandlers.length);
            SirectorUtil.copy(Arrays.asList(eventHandlers), this.eventHandlers);

            return this;
        }
    }

}
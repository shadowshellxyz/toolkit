package xyz.shadowshell.toolkit.sirector.core;

import xyz.shadowshell.toolkit.sirector.SirectorCallback;
import xyz.shadowshell.toolkit.sirector.SirectorEventHandler;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Sirector
 *
 * @author shadow
 */
public class Sirector<Event> {

    /**
     * 线程池执行器
     */
    private final ThreadPoolExecutor executor;
    /**
     * 事件Script
     */
    private final Script<Event> script;
    /**
     * 运行时构造器
     */
    private ScriptRuntimeBuilder<Event> runtimeBuilder;
    /**
     * 是否已就绪
     */
    private volatile boolean ready = false;

    /**
     * 构造函数
     *
     * @param executor 线程池执行器
     */
    public Sirector(ThreadPoolExecutor executor) {
        this.executor = executor;
        this.script = new Script<>();
    }

    /**
     * 开始
     *
     * @param eventHandlers 事件处理器列表
     * @return
     */
    public SirectorEventHandlerGroup<Event> begin(SirectorEventHandler<Event>... eventHandlers) {
        return script.start(eventHandlers);
    }

    /**
     * 在指定事件处理器之后执行指定事件处理器列表
     *
     * @param eventHandlers 事件处理器列表
     * @return
     */
    public SirectorEventHandlerGroup<Event> after(SirectorEventHandler<Event>... eventHandlers) {
        return script.after(eventHandlers);
    }

    /**
     * 发布
     *
     * @param event   事件
     * @param timeout 超时时间
     * @return
     */
    public Event publish(Event event, long timeout) {
        if (!ready) {
            throw new IllegalStateException("sirector not started.");
        }
        return runtimeBuilder.build(event, timeout).run();
    }

    /**
     * 发布
     *
     * @param event 事件
     * @return
     */
    public Event publish(Event event) {
        return publish(event, (long) 0);
    }

    /**
     * 发布
     *
     * @param event    事件
     * @param callback 回调函数
     */
    public void publish(Event event, SirectorCallback<Event> callback) {
        if (!ready) {
            throw new IllegalStateException("sirector not started.");
        }
        if (callback == null) {
            throw new IllegalArgumentException("callback can not be null");
        }
        runtimeBuilder.build(event, 0, callback).run();
    }

    /**
     * 是否已就绪
     *
     * @return
     */
    public boolean isReady() {
        return ready;
    }

    /**
     * 标注已就绪
     */
    public synchronized void ready() {
        if (!ready) {
            script.ready();
            runtimeBuilder = new ScriptRuntimeBuilder<>(script, executor);
            ready = true;
        }
    }
}
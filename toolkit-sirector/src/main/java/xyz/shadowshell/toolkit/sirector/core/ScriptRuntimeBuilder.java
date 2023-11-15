package xyz.shadowshell.toolkit.sirector.core;

import xyz.shadowshell.toolkit.sirector.SirectorCallback;
import xyz.shadowshell.toolkit.sirector.SirectorEventHandler;

import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 运行时构造器
 *
 * @author shadow
 */
class ScriptRuntimeBuilder<Event> {

    /**
     * 事件Script
     */
    private final Script<Event> script;
    /**
     * 线程池执行器
     */
    private final ThreadPoolExecutor executor;

    private IdentityHashMap<SirectorEventHandler<Event>, EventProcessor<Event>> processorMap = new IdentityHashMap<>();

    /**
     * 构造函数
     *
     * @param script   事件Script
     * @param executor 线程池执行器
     */
    ScriptRuntimeBuilder(Script<Event> script, ThreadPoolExecutor executor) {
        this.script = script;
        this.executor = executor;
        prepare();
    }

    /**
     * 准备工作
     */
    private void prepare() {

        //事件处理器被动依赖映射(key:事件处理器,value:被依赖的事件处理器列表))
        Map<SirectorEventHandler<Event>, List<SirectorEventHandler<Event>>> dependedHandlerMap = SirectorUtil.copyMap(script.getDenpendedHandlers());
        //事件处理器依赖映射(key:事件处理器,value:依赖的事件处理器列表)
        Map<SirectorEventHandler<Event>, List<SirectorEventHandler<Event>>> dependingHandlerMap = new HashMap<>();
        SirectorUtil.copyKeys(dependedHandlerMap.keySet(), dependingHandlerMap);
        SirectorUtil.reverseCopyMap(dependedHandlerMap, dependingHandlerMap);

        ScriptEndEventHandler scriptEndEventHandler = new ScriptEndEventHandler();
        List<SirectorEventHandler<Event>> scriptEndDependingHandlers = new ArrayList<>(1);
        SirectorUtil.fillDefaultValueIfEmpty(dependedHandlerMap, scriptEndEventHandler,
                noDependedHandler -> scriptEndDependingHandlers.add(noDependedHandler)
        );
        dependedHandlerMap.put(scriptEndEventHandler, new ArrayList<>(0));
        dependingHandlerMap.put(scriptEndEventHandler, scriptEndDependingHandlers);

        for (Map.Entry<SirectorEventHandler<Event>, List<SirectorEventHandler<Event>>> entry : dependedHandlerMap.entrySet()) {

            SirectorEventHandler<Event> handler = entry.getKey();
            List<SirectorEventHandler<Event>> dependedHandlers = entry.getValue();

            int handlerDependingCount = SirectorUtil.size(dependingHandlerMap.get(handler));
            EventProcessor<Event> processor;
            if (handler == scriptEndEventHandler) {
                processor = new ScriptEndEventProcessor(handler, handlerDependingCount, dependedHandlers);
            } else {
                processor = new EventProcessor<>(handler, handlerDependingCount, dependedHandlers);
            }
            processorMap.put(handler, processor);
        }
    }

    /**
     * 构建
     *
     * @param event
     * @param timeout
     * @return
     */
    ScriptRuntime<Event> build(Event event, long timeout) {
        return build(event, timeout, null);
    }

    /**
     * 构建
     *
     * @param event    事件
     * @param timeout  超时事件(单位:毫秒)
     * @param callback 回调函数
     * @return 事件Script运行时
     */
    ScriptRuntime<Event> build(Event event, long timeout, SirectorCallback<Event> callback) {

        IdentityHashMap<SirectorEventHandler<Event>, EventProcessor<Event>> freshProcessorMap = new IdentityHashMap<>(processorMap.size());
        ScriptRuntime<Event> runtime = new ScriptRuntime<>(event, executor, freshProcessorMap, callback, timeout);

        for (Map.Entry<SirectorEventHandler<Event>, EventProcessor<Event>> entry : processorMap.entrySet()) {
            SirectorEventHandler handler = entry.getKey();
            EventProcessor<Event> freshProcessor = (EventProcessor<Event>) (entry.getValue() == null ? null : entry.getValue().clone());
            freshProcessor.init(runtime, event);
            freshProcessorMap.put(handler, freshProcessor);
        }
        return runtime;
    }

    /**
     * ScriptEndEventProcessor
     */
    private class ScriptEndEventProcessor extends EventProcessor<Event> {

        /**
         * 构造函数
         *
         * @param eventHandler          事件处理器
         * @param depdending            依赖计数器
         * @param dependedEventHandlers 被依赖的事件处理器列表
         */
        ScriptEndEventProcessor(SirectorEventHandler<Event> eventHandler, int depdending,
                                List<SirectorEventHandler<Event>> dependedEventHandlers) {
            super(eventHandler, depdending, dependedEventHandlers);
        }

        @Override
        public void run() {
            runtime.markAsCompleted();
        }
    }

    /**
     * Script结束事件处理器
     */
    private class ScriptEndEventHandler implements SirectorEventHandler<Event> {

        @Override
        public void onEvent(Event event) {

        }
    }
}
package net.shadowdefender.toolkit.sirector.core;

import net.shadowdefender.toolkit.sirector.SirectorEventHandler;
import net.shadowdefender.toolkit.sirector.SirectorException;

import java.util.*;

/**
 * 事件处理器分组
 *
 * @author shadow
 */
class Script<Event> {

	/** 是否Ready标志*/
	private boolean ready = false;
	/** 描述*/
	private final IdentityHashMap<SirectorEventHandler<Event>, List<SirectorEventHandler<Event>>> denpendedHandlers = new IdentityHashMap<>();

	/**
	 * 于什么之后...
	 *
	 * @param eventHandlers 事件处理器列表
	 * @return
	 */
	public synchronized SirectorEventHandlerGroup<Event> after(final SirectorEventHandler<Event>... eventHandlers) {
		if (ready) {
			throw new SirectorException("script has ready, cannot be edit any more.");
		}
		for (SirectorEventHandler<Event> handler : eventHandlers) {
			if (!denpendedHandlers.containsKey(handler)) {
				throw new IllegalStateException(
						"event handler is not in script yet.");
			}
		}
		return start(eventHandlers);
	}

	/**
	 * 启动
	 *
	 * @param eventHandlers 事件处理器列表
	 * @return 事件处理器分组
	 */
	public synchronized SirectorEventHandlerGroup<Event> start(final SirectorEventHandler<Event>... eventHandlers) {

		if (ready) {
			throw new IllegalStateException(
					"script has ready, cannot be edit any more.");
		}
		return new SirectorEventHandlerGroup<>(this, Arrays.asList(eventHandlers));
	}

	/**
	 * 获取依赖关系
	 *
	 * @return
	 */
	public synchronized Map<SirectorEventHandler<Event>, List<SirectorEventHandler<Event>>> getDenpendedHandlers() {
		return denpendedHandlers;
	}

	/**
	 * 标注已就绪
	 */
	public synchronized void ready() {
		ready = true;
	}

	/**
	 * 判断是否已就绪
	 *
	 * @return
	 */
	public synchronized boolean isReady() {
		return ready;
	}

	/**
	 * 添加依赖
	 *
	 * @param eventHandler 事件处理器
	 * @param dependedEventHandler 被依赖的处理器
	 */
	void addDependency(SirectorEventHandler<Event> eventHandler, SirectorEventHandler<Event> dependedEventHandler) {

		if (eventHandler == null || dependedEventHandler == null) {
			return;
		}

		List<SirectorEventHandler<Event>> dependedEventHandlers = denpendedHandlers.get(eventHandler);
		if (dependedEventHandlers == null) {
			dependedEventHandlers = new ArrayList<>(1);
			denpendedHandlers.put(eventHandler, dependedEventHandlers);
		}

		dependedEventHandlers.add(dependedEventHandler);
	}
}
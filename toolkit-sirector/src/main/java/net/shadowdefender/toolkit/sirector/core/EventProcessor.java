package net.shadowdefender.toolkit.sirector.core;

import net.shadowdefender.toolkit.sirector.SirectorEventHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 事件处理器
 *
 * @author shadow
 */
class EventProcessor<Event> implements Runnable, Cloneable {

	/** 运行时*/
	protected ScriptRuntime<Event> runtime;
	/** 事件处理器*/
	private final SirectorEventHandler<Event> eventHandler;
	/** 未满足依赖数*/
	private volatile int unsatisfiedDepdendings;
	/** 被依赖的事件处理器列表*/
	private final List<SirectorEventHandler<Event>> dependedEventHandlers;

	/** 事件*/
	private Event event;
	/** 锁*/
	private Lock lock;

	/**
	 * 构造函数
	 *
	 * @param eventHandler 事件处理器
	 * @param depdendingCount 依赖计数器
	 * @param dependedEventHandlers 被依赖的事件处理器列表
	 */
	EventProcessor(SirectorEventHandler<Event> eventHandler, int depdendingCount, List<SirectorEventHandler<Event>> dependedEventHandlers) {
		this.eventHandler = eventHandler;
		this.unsatisfiedDepdendings = depdendingCount;
		this.dependedEventHandlers = dependedEventHandlers;
	}

	/**
	 * 初始化
	 *
	 * @param runtime 运行时
	 * @param event 事件
	 */
	void init(ScriptRuntime<Event> runtime, Event event) {
		this.runtime = runtime;
		this.event = event;
	}

	@Override
	public void run() {
		try {

			//响应事件
			eventHandler.onEvent(event);
			if (SirectorUtil.isCollectionEmpty(dependedEventHandlers)) {
				return;
			}

			List<EventProcessor<Event>> readyProcessors = new ArrayList<>(dependedEventHandlers.size());
			for (SirectorEventHandler<Event> dependedEventHandler : dependedEventHandlers) {
				EventProcessor<Event> processor = runtime.getProcessor(dependedEventHandler);
				if (processor == null) {
					continue;
				}
				if (processor.decreaseUnsatisfiedDependcies() == 0) {
					readyProcessors.add(processor);
				}
			}

			for (int i = (readyProcessors.size() - 1); i > 0; i--) {
				EventProcessor<Event> readyProcessor = readyProcessors.get(i);
				if (readyProcessor == null) {
					continue;
				}
				runtime.startProcessor(readyProcessor);
			}
			readyProcessors.get(0).run();

		} catch (Exception e) {
			runtime.markAsError(e);
		}
	}

	/**
	 * 是否有未得到满足依赖
	 *
	 * @return
	 */
	boolean hasUnsatisfiedDependcies() {
		lock.lock();
		try {
			return unsatisfiedDepdendings != 0;
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 递减未满足的依赖
	 *
	 * @return
	 */
	private int decreaseUnsatisfiedDependcies() {
		lock.lock();
		try {
			return -- unsatisfiedDepdendings;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public Object clone() {
		try {
			EventProcessor<Event> cloned = (EventProcessor<Event>) super.clone();
			cloned.lock = new ReentrantLock();
			return cloned;
		} catch (Exception e) {
			throw new InternalError();
		}
	}
}
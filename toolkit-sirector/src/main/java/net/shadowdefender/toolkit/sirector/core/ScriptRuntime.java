package net.shadowdefender.toolkit.sirector.core;

import net.shadowdefender.toolkit.sirector.SirectorCallback;
import net.shadowdefender.toolkit.sirector.SirectorEventHandler;
import net.shadowdefender.toolkit.sirector.SirectorException;
import net.shadowdefender.toolkit.sirector.SirectorTimeoutException;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author shadow
 */
class ScriptRuntime<Event> {

	/** 事件*/
	private final Event event;
	/** 线程池执行器*/
	private final ThreadPoolExecutor executor;

	private final CountDownLatch completeLatch;

	private final Map<SirectorEventHandler<Event>, EventProcessor<Event>> processorMap;

	/** 回调函数*/
	private final SirectorCallback<Event> callback;
	/** 超时时间*/
	private final long timeout;
	/** 错误*/
	private volatile Throwable error;
	/** 读写锁*/
	private final Lock lock = new ReentrantLock();
	/** 是否已取消*/
	private volatile boolean canceled = false;

	private final ArrayList<Future<?>> futures;

	private final static ArrayList<Future<?>> EmptyFutures = new ArrayList<>(0);

	ScriptRuntime(Event event, ThreadPoolExecutor executor, Map<SirectorEventHandler<Event>, EventProcessor<Event>> processorMap,
				  SirectorCallback<Event> callback, long timeout) {
		this.event = event;
		this.executor = executor;
		this.processorMap = processorMap;
		this.callback = callback;
		if (callback == null) {
			completeLatch = new CountDownLatch(1);
		} else {
			completeLatch = null;
		}
		this.timeout = timeout;
		if (this.timeout > 0) {
			futures = new ArrayList<>(1);
		} else {
			futures = EmptyFutures;
		}
	}

	Event run() {
		ArrayList<EventProcessor<Event>> processesWithNoDependencies = getProcessedWithNoDependencies();
		for (EventProcessor<Event> process : processesWithNoDependencies) {
			startProcessor(process);
		}
		waitIfNecessary();
		return event;
	}

	private ArrayList<EventProcessor<Event>> getProcessedWithNoDependencies() {
		ArrayList<EventProcessor<Event>> processesWithNoDependencies = new ArrayList<>(1);
		for (EventProcessor<Event> processor : processorMap.values()) {
			if (!processor.hasUnsatisfiedDependcies()) {
				processesWithNoDependencies.add(processor);
			}
		}
		return processesWithNoDependencies;
	}

	/**
	 * 标注完成
	 */
	void markAsCompleted() {
		if (callback == null) {
			completeLatch.countDown();
		} else {
			callback.onSuccess(event);
		}
	}

	/**
	 * 标注错误
	 *
	 * @param error
	 */
	void markAsError(Throwable error) {
		if (callback == null) {
			this.error = error;
			completeLatch.countDown();
		} else {
			callback.onError(event, error);
		}
	}

	/**
	 * 取消
	 */
	private void cancel() {
		lock.lock();
		try {
			canceled = true;
			for (Future<?> future : futures) {
				future.cancel(true);
			}
		} finally {
			lock.unlock();
		}
	}

	private void waitIfNecessary() {
		/**
		 * Synchronize style, we should block script call thread. when all event
		 * handler processes are done, we can wake up the call thread. If any
		 * exception is thrown when call event handler process, we should catch
		 * the exception, and throw the exception in the script call thread.
		 */
		if (callback == null) {
			try {
				if (timeout > 0) {
					if (!completeLatch.await(timeout, TimeUnit.MILLISECONDS)) {
						cancel();
						throw new SirectorTimeoutException();
					}
				} else {
					completeLatch.await();
				}

				if (error != null) {
					throw new SirectorException(error);
				}
			} catch (InterruptedException e) {
				throw new SirectorException(e);
			}
		}
	}

	/**
	 * 获取回调函数
	 *
	 * @return
	 */
	SirectorCallback<Event> getCallback() {
		return callback;
	}

	/**
	 * 获取处理器
	 *
	 * @param eventHandler
	 * @return
	 */
	EventProcessor<Event> getProcessor(SirectorEventHandler<Event> eventHandler) {
		return processorMap.get(eventHandler);
	}

	/**
	 * 开始执行Processor
	 *
	 * @param processor
	 */
	void startProcessor(EventProcessor<Event> processor) {
		if (timeout > 0) {
			lock.lock();
			try {
				if (!canceled) {
					futures.add(executor.submit(processor));
				}
			} finally {
				lock.unlock();
			}
		} else {
			executor.submit(processor);
		}
	}
}
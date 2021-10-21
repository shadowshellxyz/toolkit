package net.shadowdefender.toolkit.template.compute;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步并行计算模板
 *
 * @param <PARAM>
 * @param <RESULT>
 *
 * @author shadow
 */
public interface AsyncParallelComputeTempalte<PARAM, RESULT> {

    /**
     * 执行计算
     *
     * @param threadPoolExecutor 执行任务的线程池
     * @param params 任务参数数组，执行引擎会为每个参数创建一个可被执行的任务。
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    default Map<PARAM, RESULT> compute(ThreadPoolExecutor threadPoolExecutor, PARAM[] params)
            throws ExecutionException, InterruptedException {
        return compute(threadPoolExecutor, params, false);
    }

    /**
     * 执行计算
     *
     * @param threadPoolExecutor 执行任务的线程池
     * @param params 任务参数数组，执行引擎会为每个参数创建一个可被执行的任务。
     * @param onlyOne 当一个计算任务被执行完成时立即返回结果，此时整个计算过程结束。
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    default Map<PARAM, RESULT> compute(ThreadPoolExecutor threadPoolExecutor, PARAM[] params, boolean onlyOne)
            throws ExecutionException, InterruptedException {
        if (isEmpty(params)) {
            return null;
        }
        List<PARAM> paramList = Arrays.asList(params);
        return compute(threadPoolExecutor, paramList, onlyOne);
    }

    /**
     * 执行计算
     *
     * @param threadPoolExecutor 执行任务的线程池
     * @param params 任务参数列表，执行引擎会为每个参数创建一个可被执行的任务。
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    default Map<PARAM, RESULT> compute(ThreadPoolExecutor threadPoolExecutor, List<PARAM> params)
            throws InterruptedException, ExecutionException {
        return compute(threadPoolExecutor, params, false);
    }

    /**
     * 执行计算
     *
     * @param threadPoolExecutor 执行任务的线程池
     * @param params 任务参数列表，执行引擎会为每个参数创建一个可被执行的任务。
     * @param isOnlyOne 当一个计算任务被执行完成时立即返回结果，此时整个计算过程结束。
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    default Map<PARAM, RESULT> compute(ThreadPoolExecutor threadPoolExecutor, List<PARAM> params, boolean isOnlyOne)
            throws InterruptedException, ExecutionException {

        // 参数校验
        if (isEmpty(params)) {
            return null;
        }

        // 结果映射
        Map<PARAM, RESULT> resultMap = new HashMap<>();

        // 单个任务 ==>> 串行计算
        if (params.size() == 1) {
            PARAM param = params.get(0);
            RESULT result = compute0(param);
            if (result != null) {
                resultMap.put(param, result);
            }
        } else {
            // 多个任务 ==>> 并行计算
            CompletionService<AsyncParallelComputeResult<PARAM, RESULT>> completionService = new ExecutorCompletionService<>(
                    threadPoolExecutor);

            // 提交任务列表
            List<Future<AsyncParallelComputeResult<PARAM, RESULT>>> futures = new ArrayList<>();
            for (final PARAM param : params) {
                futures.add(completionService.submit(new Callable<AsyncParallelComputeResult<PARAM, RESULT>>() {
                    @Override
                    public AsyncParallelComputeResult<PARAM, RESULT> call() throws Exception {
                        RESULT result = compute0(param);
                        if (result == null) {
                            return null;
                        }
                        return new AsyncParallelComputeResult<>(param, result);
                    }
                }));
            }

            // 获取任务结果
            for (Future<AsyncParallelComputeResult<PARAM, RESULT>> future : futures) {
                AsyncParallelComputeResult<PARAM, RESULT> result = future.get();
                if (result == null) {
                    continue;
                }
                resultMap.put(result.getParam(), result.getResult());

                // 判断是否需要提前返回
                if (isOnlyOne) {
                    break;
                }
            }

            // 判断是否需要提前取消
            if (isOnlyOne) {
                for (Future<AsyncParallelComputeResult<PARAM, RESULT>> future : futures) {
                    future.cancel(true);
                }
            }
        }
        return resultMap;
    }

    /**
     * 是否为空
     *
     * @param collection 集合
     * @return
     */
    default boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 是否为空
     *
     * @param array 数组
     * @param <E>
     * @return
     */
    default <E> boolean isEmpty(E[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 异步并行计算结果
     *
     * @param <PARAM> 参数
     * @param <RESULT> 结果
     */
    class AsyncParallelComputeResult<PARAM, RESULT> implements Serializable {

        /**
         * 参数
         */
        private PARAM param;

        /**
         * 结果
         */
        private RESULT result;

        /**
         * 构造函数
         *
         * @param param 参数
         * @param result 结果
         */
        public AsyncParallelComputeResult(PARAM param, RESULT result) {
            this.param = param;
            this.result = result;
        }

        /**
         * Getter method for property <tt>param</tt>.
         *
         * @return property value of param
         */
        public PARAM getParam() {
            return param;
        }

        /**
         * Setter method for property <tt>param</tt>.
         *
         * @param param value to be assigned to property param
         */
        public void setParam(PARAM param) {
            this.param = param;
        }

        /**
         * Getter method for property <tt>result</tt>.
         *
         * @return property value of result
         */
        public RESULT getResult() {
            return result;
        }

        /**
         * Setter method for property <tt>result</tt>.
         *
         * @param result value to be assigned to property result
         */
        public void setResult(RESULT result) {
            this.result = result;
        }
    }

    /**
     * 完成单个任务计算功能
     *
     * @param param 参数
     * @return
     */
    RESULT compute0(PARAM param);
}
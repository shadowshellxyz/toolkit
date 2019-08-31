package org.walkerljl.toolkit.standard.model.paging;

import java.util.List;

import org.walkerljl.toolkit.standard.model.BaseModel;

/**
 * 分页器
 *
 * @author xingxun
 */
public class Paginator<T> extends BaseModel {

    private static final long serialVersionUID = 1L;

    /** 默认每页最多数据条数*/
    public static final int DEFAULT_MAX_PAGE_SIZE = 200;
    /** 默认每页最少数据条数*/
    public static final int DEFAULT_MIN_PAGE_SIZE = 10;

    /** 当前页码*/
    private int currentPage = 1;
    /** 每页数据条数*/
    private int pageSize    = DEFAULT_MIN_PAGE_SIZE;
    /** 数据列表*/
    private List<T> dataList;
    /** 是否查询总数*/
    private boolean queryTotalCount = true;
    /** 总的数据条数*/
    private int totalCount;

    /**
     * 默认构造函数
     */
    public Paginator() {}

    /**
     * 构造函数
     *
     * @param pageSize 每页数据条数
     */
    public Paginator(int pageSize) {
        this(1, pageSize, 0);
    }

    /**
     * 构造函数
     *
     * @param paginator 指定的分页器
     * @param <E>
     */
    public <E> Paginator(Paginator<E> paginator) {
        this(paginator.getCurrentPage(), paginator.getPageSize(), paginator.getTotalCount());
        setQueryTotalCount(paginator.isQueryTotalCount());
    }

    /**
     * 构造函数
     *
     * @param currentPage 当前页码
     * @param pageSize 每页数据条数
     */
    public Paginator(int currentPage, int pageSize) {
        this(currentPage, pageSize, 0);
    }

    /**
     * 构造函数
     *
     * @param currentPage 当前页码
     * @param pageSize 每页数据条数
     * @param totalCount 总的数据条数
     */
    public Paginator(int currentPage, int pageSize, int totalCount) {
        setCurrentPage(currentPage);
        setPageSize(pageSize);
        setTotalCount(totalCount);
    }

    /**
     * 获取开始索引
     *
     * @return
     */
    public int getBeginIndex() {
        return (getCurrentPage() - 1) * getPageSize();
    }

    /**
     * 获取结束索引
     *
     * @return
     */
    public int getEndIndex() {
        return getCurrentPage() * getPageSize();
    }

    /**
     * 是否第一页
     *
     * @return
     */
    public boolean isFirstPage() {
        return getCurrentPage() <= 1;
    }

    /**
     * 是否末页
     *
     * @return
     */
    public boolean isLastPage() {
        return getCurrentPage() >= getPageCount();
    }

    /**
     * 获取下一页页码
     *
     * @return
     */
    public int getNextPage() {
        return isLastPage() ? getCurrentPage() : getCurrentPage() + 1;
    }

    /**
     * 获取上一页页码
     *
     * @return
     */
    public int getPreviousPage() {
        return isFirstPage() ? 1 : getCurrentPage() - 1;
    }

    /**
     * 增加页码
     *
     * @param increment 增量
     */
    public void incrementPage(int increment) {
        setCurrentPage(getCurrentPage() + increment);
    }

    //getter and setters

    /**
     * 获取当前页页码
     *
     * @return
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * 设置当前页
     *
     * @param currentPage
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = (currentPage <= 0 ? 1 : currentPage);
    }

    /**
     * 取得总页数
     *
     * @return
     */
    public int getPageCount() {
        int totalCount = getTotalCount();
        int pageSize = getPageSize();
        return (totalCount % pageSize == 0) ? (totalCount / pageSize) : (totalCount / pageSize + 1);
    }

    /**
     * 取总记录数.
     *
     * @return
     */
    public int getTotalCount() {
        return this.totalCount;
    }

    /**
     * 设置总记录条数
     *
     * @param totalCount
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 获取每页数据条数
     *
     * @return
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置每页数据条数
     *
     * @param pageSize 每页数据条数
     */
    public void setPageSize(int pageSize) {
        if (pageSize > DEFAULT_MAX_PAGE_SIZE) {
            pageSize = DEFAULT_MAX_PAGE_SIZE;
        }
        this.pageSize = pageSize;
    }

    /**
     * 该页是否有下一页.
     *
     * @return
     */
    public boolean hasNextPage() {
        return getCurrentPage() < getPageCount();
    }

    /**
     * 该页是否有上一页.
     *
     * @return
     */
    public boolean hasPreviousPage() {
        return getCurrentPage() > 1;
    }

    /**
     * 获取数据列表
     *
     * @return
     */
    public List<T> getDataList() {
        return dataList;
    }

    /**
     * 设置数据列表
     *
     * @param dataList 数据列表
     */
    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    /**
     * 是否查询总数量
     *
     * @return
     */
    public boolean isQueryTotalCount() {
        return queryTotalCount;
    }

    /**
     * 设置是否查询总数量
     *
     * @param queryTotalCount 是否查询总数量
     */
    public void setQueryTotalCount(boolean queryTotalCount) {
        this.queryTotalCount = queryTotalCount;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
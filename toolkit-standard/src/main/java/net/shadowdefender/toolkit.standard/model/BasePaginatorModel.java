package net.shadowdefender.toolkit.standard.model;

import net.shadowdefender.toolkit.standard.model.paging.Paginator;
import net.shadowdefender.toolkit.standard.model.param.BaseParam;

/**
 * 基础分页器模型
 *
 * @author shadow
 */
public class BasePaginatorModel extends BaseParam {

    private static final long serialVersionUID = 3942271687320226945L;

    /** 默认每页最少数据条数*/
    public static final int DEFAULT_MIN_PAGE_SIZE = 10;

    /** 当前页码*/
    private int currentPage = 1;
    /** 每页数据条数*/
    private int pageSize = DEFAULT_MIN_PAGE_SIZE;
    /** 是否查询总数*/
    private boolean queryTotalCount = false;
    /** 总的数据条数*/
    private int totalCount;

    /**
     * 初始化
     *
     * @param inputModel
     */
    public void init(Paginator inputModel) {
        if (inputModel == null) {
            return;
        }
        setCurrentPage(inputModel.getCurrentPage());
        setPageSize(inputModel.getPageSize());
        setQueryTotalCount(inputModel.isQueryTotalCount());
        setTotalCount(inputModel.getTotalCount());
    }

    /**
     * Getter method for property <tt>currentPage</tt>.
     *
     * @return property value of currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * Setter method for property <tt>currentPage</tt>.
     *
     * @param currentPage  value to be assigned to property currentPage
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * Getter method for property <tt>pageSize</tt>.
     *
     * @return property value of pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Setter method for property <tt>pageSize</tt>.
     *
     * @param pageSize  value to be assigned to property pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Getter method for property <tt>queryTotalCount</tt>.
     *
     * @return property value of queryTotalCount
     */
    public boolean isQueryTotalCount() {
        return queryTotalCount;
    }

    /**
     * Setter method for property <tt>queryTotalCount</tt>.
     *
     * @param queryTotalCount  value to be assigned to property queryTotalCount
     */
    public void setQueryTotalCount(boolean queryTotalCount) {
        this.queryTotalCount = queryTotalCount;
    }

    /**
     * Getter method for property <tt>totalCount</tt>.
     *
     * @return property value of totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * Setter method for property <tt>totalCount</tt>.
     *
     * @param totalCount  value to be assigned to property totalCount
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
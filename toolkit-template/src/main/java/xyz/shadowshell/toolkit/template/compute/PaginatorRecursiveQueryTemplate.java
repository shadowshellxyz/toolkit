package xyz.shadowshell.toolkit.template.compute;

import java.util.Collection;
import java.util.List;

import xyz.shadowshell.toolkit.standard.model.paging.Paginator;

/**
 * 基于分页器的递归查询模版
 *
 * @author shadow
 */
public abstract class PaginatorRecursiveQueryTemplate<PARAM, RESULT> extends RecursiveQueryTemplate<PARAM, RESULT> {

    protected Paginator<RESULT> paginator = new Paginator<>(0, Paginator.DEFAULT_MAX_PAGE_SIZE);

    @Override
    protected boolean isContinue(PARAM param) {
        return size(paginator.getDataList()) >= paginator.getPageSize();
    }

    @Override
    protected List<RESULT> query0(PARAM param) {

        List<RESULT> partResultList = query00(param);
        paginator.incrementPage(1);
        paginator.setDataList(partResultList);

        return partResultList;
    }

    private int size(Collection<?> collection) {
        return collection == null ? 0 : collection.size();
    }

    protected abstract List<RESULT> query00(PARAM param);
}
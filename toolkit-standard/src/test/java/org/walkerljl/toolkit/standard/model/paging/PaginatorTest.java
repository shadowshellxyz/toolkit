package org.walkerljl.toolkit.standard.model.paging;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.toolkit.standard.BaseUnitTest;

/**
 * PaginatorTest
 *
 * @author xingxun
 */
public class PaginatorTest extends BaseUnitTest {

    @Test
    public void test() {

        Paginator paginator = new Paginator();
        Assert.assertEquals(1, paginator.getCurrentPage());
        Assert.assertEquals(paginator.getPageSize(), Paginator.DEFAULT_MIN_PAGE_SIZE);
        Assert.assertEquals(0, paginator.getBeginIndex());
        Assert.assertEquals(paginator.getCurrentPage() * paginator.getPageSize(), paginator.getEndIndex());
        Assert.assertTrue(paginator.isFirstPage());
        Assert.assertTrue(paginator.isLastPage());

        paginator = new Paginator(2);
        Assert.assertEquals(1, paginator.getCurrentPage());
        Assert.assertEquals(paginator.getPageSize(), 2);
        Assert.assertEquals((paginator.getCurrentPage() - 1) * paginator.getPageSize(), paginator.getBeginIndex());
        Assert.assertEquals(paginator.getCurrentPage() * paginator.getPageSize(), paginator.getEndIndex());

        paginator = new Paginator(2, 200);
        Assert.assertEquals(2, paginator.getCurrentPage());
        Assert.assertEquals(200, paginator.getPageSize());
        paginator.setTotalCount(1000);
        Assert.assertEquals(5, paginator.getPageCount());
        paginator.setTotalCount(999);
        Assert.assertEquals(5, paginator.getPageCount());
        paginator.setTotalCount(1001);
        Assert.assertEquals(6, paginator.getPageCount());
        Assert.assertFalse(paginator.isFirstPage());
        Assert.assertFalse(paginator.isLastPage());
        paginator.setCurrentPage(6);
        Assert.assertTrue(paginator.isLastPage());

        paginator = new Paginator(2, 1000);
        Assert.assertEquals(2, paginator.getCurrentPage());
        Assert.assertEquals(Paginator.DEFAULT_MAX_PAGE_SIZE, paginator.getPageSize());
    }
}
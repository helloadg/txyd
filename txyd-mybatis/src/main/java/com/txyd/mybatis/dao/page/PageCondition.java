package com.txyd.mybatis.dao.page;

/**
 * 分页类
 */

public class PageCondition {
    private static final boolean defaultDisablePagePlugin = false;

    public static final int defaultPageStartNum = 1;

    /**
     * 查询时：每页大小
     */
    private final int pageSize;

    /**
     * 查询时：页码,默认从1开始
     */
    private final int pageNum;

    /**
     * 总条数
     */
    private int totalCount;

    /**
     * 是否关闭分页插件
     */
    private boolean disablePagePlugin;

    /**
     * @param pageSize :每页大小
     * @param pageNum  ：页码,默认从1开始
     */
    public PageCondition(int pageSize, int pageNum) {
        pageNum = pageNum > 0 ? pageNum : defaultPageStartNum;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.disablePagePlugin = defaultDisablePagePlugin;
    }

    /**
     * @param pageSize          : 每页大小
     * @param pageNum           ：页码,默认从1开始
     * @param disablePagePlugin : 是否启用分页
     */
    public PageCondition(int pageSize, int pageNum, boolean disablePagePlugin) {
        pageNum = pageNum > 0 ? pageNum : defaultPageStartNum;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.disablePagePlugin = disablePagePlugin;
    }



    public int getPageSize() {
        return pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isDisablePagePlugin() {
        return disablePagePlugin;
    }

    public void setDisablePagePlugin(boolean disablePagePlugin) {
        this.disablePagePlugin = disablePagePlugin;
    }
}

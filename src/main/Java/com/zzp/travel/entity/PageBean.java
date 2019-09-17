package com.zzp.travel.entity;

import java.util.List;

/**
 * 分页数据类
 */
public class PageBean<T> {
    private int totalRows;
    private int totalPage;
    private int currentPage;
    private int rows;//当前页面的总条数
    private List<T> list;

    @Override
    public String toString() {
        return "PageBean{" +
                "totalRows=" + totalRows +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                ", rows=" + rows +
                ", list=" + list +
                '}';
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}

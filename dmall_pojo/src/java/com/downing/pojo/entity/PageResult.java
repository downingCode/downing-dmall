package com.downing.pojo.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author downing
 * @descript 分页结果对象
 */
public class PageResult<T> implements Serializable {

    private Long totalCount;
    private List<T> rows;

    public PageResult() {
    }

    public PageResult(Long totalCount, List<T> rows) {
        this.totalCount = totalCount;
        this.rows = rows;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}

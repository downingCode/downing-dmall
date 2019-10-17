package com.downing.sort;

import java.util.List;

/**
 * @author downing
 * @descript 抽象排序策略接口
 */
public interface SortInterface<T> {

    /**
     * 排序接口
     *
     * @param sortList
     */
    void sort(List<T> sortList);
}
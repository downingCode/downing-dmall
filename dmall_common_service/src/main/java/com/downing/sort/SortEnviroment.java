package com.downing.sort;

import java.util.List;

/**
 * @author downing
 * @descript 排序环境角色
 */
public class SortEnviroment<T> {

    private SortInterface<T> sortInterface;

    public SortEnviroment(SortInterface<T> sortInterface) {
        this.sortInterface = sortInterface;
    }

    public SortInterface<T> getSortInterface() {
        return sortInterface;
    }

    public void setSortInterface(SortInterface<T> sortInterface) {
        this.sortInterface = sortInterface;
    }

    public void sort(List<T> list) {
        sortInterface.sort(list);
    }
}

package com.downing.service.goods;

import com.downing.pojo.goods.Category;

import java.util.List;

/**
 * @author downing
 * @descript
 */
public interface CategoryService {
    /**
     * 根据父分类id查询
     *
     * @return
     */
    List<Category> findByParentId(int parentId);
}

package com.downing.service.goods;

import com.downing.pojo.entity.PageResult;
import com.downing.pojo.goods.Brand;

import java.util.List;
import java.util.Map;

/**
 * @author downing
 * @descript
 */
public interface BrandService {

    /**
     * 查询全部
     *
     * @return
     */
    List<Brand> findAll();

    /**
     * 分页条件查询
     *
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    PageResult<Brand> findPage(Map<String, Object> searchMap, int page, int size);

}

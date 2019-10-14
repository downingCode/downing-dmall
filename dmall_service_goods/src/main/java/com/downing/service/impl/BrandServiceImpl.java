package com.downing.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.downing.dao.BrandMapper;
import com.downing.pojo.entity.PageResult;
import com.downing.pojo.goods.Brand;
import com.downing.service.goods.BrandService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @author downing
 * @descript
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    @Override
    public PageResult<Brand> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page, size);
        Example example = new Example(Brand.class);
        if (searchMap != null) {
            for (String keyStr : searchMap.keySet()) {
                example.createCriteria().andLike(keyStr, "%" + searchMap.get(keyStr) + "%");
            }
        }
        Page<Brand> brands = (Page<Brand>) brandMapper.selectByExample(example);
        return new PageResult<>(brands.getTotal(), brands.getResult());
    }
}

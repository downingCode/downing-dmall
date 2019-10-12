package com.downing.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.downing.dao.BrandMapper;
import com.downing.pojo.goods.Brand;
import com.downing.service.goods.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
}

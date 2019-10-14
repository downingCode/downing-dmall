package com.downing.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.downing.dao.CategoryMapper;
import com.downing.pojo.goods.Category;
import com.downing.service.goods.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author downing
 * @descript
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findByParentId(int parentId) {
        Example example = new Example(Category.class);
        example.setOrderByClause("seq DESC");
        example.createCriteria().andEqualTo("parentId", parentId).andEqualTo("isShow", true).andEqualTo("deleted", false);
        return categoryMapper.selectByExample(example);
    }
}

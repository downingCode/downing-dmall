package com.downing.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONArray;
import com.downing.dao.*;
import com.downing.pojo.goods.*;
import com.downing.service.goods.GoodsService;
import com.downing.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author downing
 * @descript
 */
@Service(interfaceClass = GoodsService.class)
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private PropertyKeyMapper propertyKeyMapper;

    @Autowired
    private PropertyValueMapper propertyValueMapper;

    @Autowired
    private PropertyRelationMapper propertyRelationMapper;

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SkuMapper skuMapper;

    /**
     * 数据格式
     * {
     * "spu":{
     * "name":"华为p30 pro",
     * "title":"超强广角，照亮你的美"
     * },
     * "skuList":[
     * {
     * "name":"华为p30 pro 蓝色 5.5寸"，
     * "property":[{"key"："颜色","valye":"蓝色"},{"key"："尺寸","value":"5.5寸"}]
     * },{
     * "name":"华为p30 pro 红色"
     * "property":[{"key"："颜色","valye":"红色"}]
     * }
     * ]
     * }
     *
     * @param goods
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveGoods(Goods goods) {
        //保存商品信息
        Spu spu = goods.getSpu();

        //新增商品先设置id
        if (spu.getId() == null) {
            spu.setId(idWorker.nextId());
            spuMapper.insertSelective(spu);
        }

        List<Sku> skuList = goods.getSkuList();
        int stock = 0;
        for (Sku sku : skuList) {
            stock += sku.getStock();
            //保存sku属性
            sku.setName(spu.getName());
            String propertyStr = sku.getProperty();
            JSONArray propertyArrty = JSONArray.parseArray(propertyStr);
            List<SkuProperty> skuPropertiesList = propertyArrty.toJavaList(SkuProperty.class);
            //保存属性信息
            saveProperty(skuPropertiesList, spu.getId(), sku);

            //保存sku信息
            sku.setSpuId(spu.getId());

            //新增sku
            if (sku.getId() == null) {
                sku.setId(idWorker.nextId());
                //insert
                skuMapper.insertSelective(sku);
            } else {
                //修改
                skuMapper.updateByPrimaryKey(sku);
            }

        }

        //更新信息
        spu.setStock(stock);
        skuList.sort(Comparator.comparingInt(Sku::getPrice));
        spu.setMinPrice(skuList.get(0).getPrice());
        spu.setMaxPrice(skuList.get(skuList.size() - 1).getPrice());
        spu.setUpdateTime(new Date());
        //新增商品
        spuMapper.updateByPrimaryKeySelective(spu);
    }

    /**
     * 保存属性及关联
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveProperty(List<SkuProperty> skuPropertiesList, Long spuId, Sku sku) {
        List<String> temporaryList = new ArrayList<>(5);

        for (SkuProperty skuProperty : skuPropertiesList) {
            //验证
            if (skuProperty.getKey() == null || skuProperty.getValue() == null) {
                throw new RuntimeException("规格名称/值不能为空");
            }
            for (String item : temporaryList) {
                if (skuProperty.getKey().equals(item)) {
                    throw new RuntimeException("同一规格名称不能相同");
                }
            }
            temporaryList.add(skuProperty.getKey());

            //不重复保存property_key
            PropertyKey propertyKey = new PropertyKey();
            propertyKey.setName(skuProperty.getKey());
            Example keyExample = new Example(PropertyKey.class);
            keyExample.createCriteria().andEqualTo("name", propertyKey.getName());
            PropertyKey propertyKey1 = propertyKeyMapper.selectByExample(keyExample).stream().findFirst().orElse(null);
            if (propertyKey1 == null) {
                propertyKeyMapper.insertSelective(propertyKey);
                propertyKey1 = propertyKey;
            }
            //不重复保存property_value
            PropertyValue propertyValue = new PropertyValue();
            propertyValue.setKeyId(propertyKey1.getId());
            propertyValue.setValue(skuProperty.getValue());
            Example valueExample = new Example(PropertyValue.class);
            valueExample.createCriteria().andEqualTo("value", propertyValue.getValue()).andEqualTo("keyId", propertyValue.getKeyId());
            PropertyValue propertyValue1 = propertyValueMapper.selectByExample(valueExample).stream().findFirst().orElse(null);
            if (propertyValue1 == null) {
                propertyValueMapper.insertSelective(propertyValue);
                propertyValue1 = propertyValue;
            }
            //保存property_relation
            PropertyRelation propertyRelation = new PropertyRelation();
            propertyRelation.setKeyId(propertyKey1.getId());
            propertyRelation.setValueId(propertyValue1.getId());
            propertyRelation.setSpuId(spuId);
            Example relationExample = new Example(PropertyRelation.class);
            relationExample.createCriteria().andAllEqualTo(propertyRelation);
            PropertyRelation propertyRelation1 = propertyRelationMapper.selectByExample(relationExample).stream().findFirst().orElse(null);
            if (propertyRelation1 == null) {
                propertyRelationMapper.insertSelective(propertyRelation);
            }
            sku.setName(sku.getName() + " " + skuProperty.getValue() + " ");
        }
    }
}

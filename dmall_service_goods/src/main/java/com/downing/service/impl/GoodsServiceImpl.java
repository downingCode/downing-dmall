package com.downing.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONArray;
import com.downing.dao.*;
import com.downing.pojo.goods.*;
import com.downing.service.goods.GoodsService;
import com.downing.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author downing
 * @descript
 */
@Service
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
     * "title":""
     * },
     * "skuList":[
     * {
     * "name":"华为p30 pro 蓝色"
     * },{
     * "name":"华为p30 pro 红色"
     * }
     * ]
     * }
     *
     * @param goods
     */
    @Override
    public void saveGoods(Goods goods) {
        //保存商品信息
        Spu spu = goods.getSpu();

        //新增商品先设置id
        if (spu.getId() == null) {
            spu.setId(idWorker.nextId());
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
            for (SkuProperty skuProperty : skuPropertiesList) {
                //保存property_key
                PropertyKey propertyKey = new PropertyKey();
                propertyKey.setName(skuProperty.getKey());
                propertyKeyMapper.insertSelective(propertyKey);
                //保存property_value
                PropertyValue propertyValue = new PropertyValue();
                propertyValue.setKeyId(propertyKey.getId());
                propertyValue.setValue(skuProperty.getValue());
                propertyValueMapper.insertSelective(propertyValue);
                //保存property_relation
                PropertyRelation propertyRelation = new PropertyRelation();
                propertyRelation.setKeyId(propertyKey.getId());
                propertyRelation.setValueId(propertyValue.getId());
                propertyRelation.setSpuId(spu.getId());
                propertyRelationMapper.insertSelective(propertyRelation);
                sku.setName(sku.getName() + " " + skuProperty.getValue() + " ");
            }

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

        //填充信息
        spu.setStock(stock);
        //todo 价格
        //spu.setMinPrice();
        //spu.setMaxPrice();
        spu.setUpdateTime(new Date());
        //新增商品
        if (spu.getId() == null) {
            spuMapper.insertSelective(spu);
        } else {
            spuMapper.updateByPrimaryKeySelective(spu);
        }


    }
}

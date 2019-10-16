package com.downing.service.goods;

import com.downing.pojo.goods.Goods;

/**
 * @author downing
 * @descript
 */
public interface GoodsService {

    /**
     * 创建商品
     *
     * @param goods
     */
    void saveGoods(Goods goods);
}

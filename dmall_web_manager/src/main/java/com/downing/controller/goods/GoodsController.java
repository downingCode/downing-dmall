package com.downing.controller.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import com.downing.pojo.entity.DowningResult;
import com.downing.pojo.goods.Goods;
import com.downing.service.goods.GoodsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author downing
 * @descript
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Reference
    private GoodsService goodsService;

    @PostMapping("/saveGoods")
    public DowningResult saveGoods(@RequestBody Goods goods) {
        goodsService.saveGoods(goods);
        return new DowningResult(200, "发布成功");
    }
}

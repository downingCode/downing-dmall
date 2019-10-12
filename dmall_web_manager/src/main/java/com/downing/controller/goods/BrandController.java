package com.downing.controller.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import com.downing.pojo.goods.Brand;
import com.downing.service.goods.BrandService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author downing
 * @descript
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    @GetMapping("/findAll")
    public List<Brand> findAll() {
        return brandService.findAll();
    }
}

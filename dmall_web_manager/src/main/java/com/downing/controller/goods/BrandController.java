package com.downing.controller.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import com.downing.pojo.entity.PageResult;
import com.downing.pojo.goods.Brand;
import com.downing.service.goods.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody(required = false) Map searchMap, int page, int size) {
        return brandService.findPage(searchMap, page, size);
    }
}

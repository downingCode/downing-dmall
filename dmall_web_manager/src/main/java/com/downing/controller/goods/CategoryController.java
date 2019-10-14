package com.downing.controller.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import com.downing.pojo.entity.DowningResult;
import com.downing.service.goods.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author downing
 * @descript
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Reference
    private CategoryService categoryService;

    @GetMapping("/findByParentId")
    public DowningResult findByParentId(@RequestParam(value = "parentId", required = false, defaultValue = "0") int parentId) {
        return new DowningResult(200, "获取成功", categoryService.findByParentId(parentId));
    }
}

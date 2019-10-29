package com.downing.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.downing.dao.MenuMapper;
import com.downing.pojo.system.Menu;
import com.downing.service.system.MenuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author downing
 * @descript
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Map> findMenuByLoginName(String loginName) {
        List<Menu> menuList = menuMapper.findMenuByLoginName(loginName);
        return findMenuByParentId(menuList, "0");
    }

    /**
     * 根据父id查询子菜单
     *
     * @param menuList
     * @param parentId
     * @return
     */
    public List<Map> findMenuByParentId(List<Menu> menuList, String parentId) {
        List<Map> menuArrayList = new ArrayList<Map>();
        for (Menu menu : menuList) {
            if (menu.getParentId().equals(parentId)) {
                Map<String, Object> menuMap = new HashMap<String, Object>(6);
                menuMap.put("id", menu.getId());
                menuMap.put("name", menu.getName());
                menuMap.put("icon", menu.getIcon());
                menuMap.put("url", menu.getUrl());
                menuMap.put("parentId", menu.getParentId());
                menuMap.put("children", findMenuByParentId(menuList, menu.getParentId()));
                menuArrayList.add(menuMap);
            }
        }
        return menuArrayList;
    }

}

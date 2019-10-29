package com.downing.service.system;

import com.downing.pojo.system.Menu;

import java.util.List;
import java.util.Map;

/**
 * @author downing
 * @descript
 */
public interface MenuService {

    /**
     * 根据登录名查询菜单列表
     *
     * @param loginName
     * @return
     */
    List<Map> findMenuByLoginName(String loginName);
}

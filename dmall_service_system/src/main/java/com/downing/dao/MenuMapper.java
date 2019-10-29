package com.downing.dao;

import com.downing.pojo.system.Menu;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author downing
 * @descript
 */
public interface MenuMapper extends Mapper<Menu> {

    /**
     * 根据登录名查询菜单
     *
     * @param username
     * @return
     */
    @Select("SELECT * FROM sys_menu WHERE id in (" +
            "SELECT menu_id FROM sys_permision_menu WHERE permision_id in (" +
            "SELECT permision_id FROM sys_role_permision WHERE role_id in (" +
            "SELECT role_id FROM sys_admin_role WHERE admin_id in (" +
            "SELECT id FROM sys_admin WHERE username = #{username}")
    List<Menu> findMenuByLoginName(String username);
}

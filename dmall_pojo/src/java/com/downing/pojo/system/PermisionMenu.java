package com.downing.pojo.system;

import javax.persistence.Table;

/**
 * @author downing
 * @descript
 */
@Table(name = "dg_permision_menu")
public class PermisionMenu {

    private Integer permisionId;

    private Integer menuId;

    public Integer getPermisionId() {
        return permisionId;
    }

    public void setPermisionId(Integer permisionId) {
        this.permisionId = permisionId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
}

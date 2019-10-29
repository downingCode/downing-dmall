package com.downing.pojo.system;

import javax.persistence.Table;

/**
 * @author downing
 * @descript
 */
@Table(name = "dg_role_permision")
public class RolePermision {

    private Integer roleId;

    private Integer permisionId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermisionId() {
        return permisionId;
    }

    public void setPermisionId(Integer permisionId) {
        this.permisionId = permisionId;
    }
}

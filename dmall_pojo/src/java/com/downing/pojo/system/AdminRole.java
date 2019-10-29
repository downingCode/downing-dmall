package com.downing.pojo.system;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * @author downing
 */
@Table(name="dg_admin_role")
public class AdminRole implements Serializable {

    @Id
    private Integer adminId;

    @Id
    private Integer roleId;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
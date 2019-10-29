package com.downing.service.system;

import com.downing.pojo.system.Admin;

/**
 * @author downing
 * @descript
 */
public interface AdminService {

    /**
     * 保存管理员
     *
     * @param admin
     */
    void saveAdmin(Admin admin);

    /**
     * 保存管理员角色
     *
     * @param adminId
     * @param roleIds
     */
    void saveAdminRoles(Integer adminId, Integer[] roleIds);
}

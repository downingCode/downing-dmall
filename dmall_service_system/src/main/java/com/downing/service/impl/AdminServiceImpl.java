package com.downing.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.downing.dao.AdminMapper;
import com.downing.dao.AdminRoleMapper;
import com.downing.pojo.system.Admin;
import com.downing.pojo.system.AdminRole;
import com.downing.service.system.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

/**
 * @author downing
 * @descript
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Override
    public void saveAdmin(Admin admin) {
        adminMapper.insertSelective(admin);
    }

    @Override
    public void saveAdminRoles(Integer adminId, Integer[] roleIds) {
        //删除已有角色
        Example example = new Example(AdminRole.class);
        example.createCriteria().andEqualTo("adminId", adminId);
        adminRoleMapper.deleteByExample(example);
        //添加
        for (Integer roleId : roleIds) {
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminId(adminId);
            adminRole.setRoleId(roleId);
            adminRoleMapper.insert(adminRole);
        }
    }
}

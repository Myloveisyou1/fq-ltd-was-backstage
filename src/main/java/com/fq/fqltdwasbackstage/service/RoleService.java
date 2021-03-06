package com.fq.fqltdwasbackstage.service;

import com.fq.fqltdwasbackstage.domain.Menu;
import com.fq.fqltdwasbackstage.domain.Role;

import java.util.List;

/**
 * @Descript:
 * @Author: liuyingjie
 * @Date: create in 2018/5/29 0029 17:38
 */
public interface RoleService {

    List<Role> findAllRole(String roleName);

    boolean updateRole(Role role);

    boolean addRole(Role role, String menus);

    boolean updateRomeMenu(Role role, String ymenus, String nmenus);

    List<Menu> findMenuByRole(Long roleId);

    boolean deleteRole(Long gid);

}

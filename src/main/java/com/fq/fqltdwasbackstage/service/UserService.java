package com.fq.fqltdwasbackstage.service;

import com.fq.fqltdwasbackstage.domain.Role;
import com.fq.fqltdwasbackstage.domain.User;
import com.fq.fqltdwasbackstage.enums.ResultEnum;
import com.fq.fqltdwasbackstage.exception.WasBackastageException;
import com.fq.fqltdwasbackstage.mapper.RoleMapper;
import com.fq.fqltdwasbackstage.mapper.UserMapper;
import com.fq.fqltdwasbackstage.utils.CommonUtil;
import com.fq.fqltdwasbackstage.utils.DatesUtils;
import com.fq.fqltdwasbackstage.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Descript:
 * @Author: liuyingjie
 * @Date: create in 2018/5/30 0030 9:19
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询所有用户
     * @param userName
     * @return
     */
    public List<User> findAllUser(String userName) {

        return userMapper.findAllUser(userName);
    }

    /**
     * 修改用户信息
     * @param bean
     * @return
     */
    public boolean updateUser(User bean) {

        User user = userMapper.findByUserName(bean.getUserName());

        if (CommonUtil.isNotEmpty(user)) {
            if (CommonUtil.isNotEmpty(bean.getEmail())) {
                user.setEmail(bean.getEmail());
            }
            if (CommonUtil.isNotEmpty(bean.getTel())) {
                user.setTel(bean.getTel());
            }
            if (CommonUtil.isNotEmpty(bean.getRoleId())) {
                Role role = roleMapper.findById(bean.getRoleId());
                if (CommonUtil.isNotEmpty(role)) {
                    user.setRoleId(bean.getRoleId());
                    user.setRoleName(role.getRoleName());
                }

            }
            if (CommonUtil.isNotEmpty(bean.getStatus())) {
                user.setStatus(bean.getStatus());
            }

            user.setUpdateTime(DatesUtils.time());
            user.setVersion(user.getVersion()+1l);
        }

        return userMapper.optUpdateUser(user);
    }

    /**
     * 根据登录名查询数据
     * @param userName
     * @return
     */
    public User findUserByName(String userName) {

        return userMapper.findByUserName(userName);
    }

    /**
     * 校验密码
     * @param userName
     * @param password
     * @return
     */
    public boolean checkPassword(String userName, String password) {

        User user = userMapper.findByUserName(userName);
        if (CommonUtil.isNotEmpty(user)) {
            String str = user.getPassword();
            if (str.equals(MD5Util.getMD5(userName+MD5Util.getMD5(password)))) {
                return true;
            } else {
                throw new WasBackastageException(ResultEnum.ERROR_OLD_PASSWORD);
            }
        }
        return false;
    }

    /**
     * 修改密码
     * @param userName
     * @param password
     * @return
     */
    public boolean updatePassword(String userName, String password) {

        User user = userMapper.findByUserName(userName);
        if (CommonUtil.isNotEmpty(user)) {
            String newPassword = MD5Util.getMD5(userName+MD5Util.getMD5(password));
            user.setPassword(newPassword);
            user.setVersion(user.getVersion()+1l);
            user.setUpdateTime(DatesUtils.time());
        }

        return userMapper.optUpdateUser(user);
    }

    /**
     * 删除用户
     * @param user
     * @return
     */
    public int deleteUser(User user) {

        String[] str = {};
        int ret = 0;
        if (user.getUserName().indexOf(",") != -1) {
            str = user.getUserName().split(",");

            for(int i=0;i<str.length;i++) {
                User bean = userMapper.findByUserName(str[i]);
                if (CommonUtil.isNotEmpty(bean)) {
                    ret += userMapper.deleteUser(bean.getGid());
                }
            }
        } else {
            User bean = userMapper.findByUserName(user.getUserName());
            if (CommonUtil.isNotEmpty(bean)) {
                ret += userMapper.deleteUser(bean.getGid());
            }
        }

        return ret;
    }

    /**
     * 新增用户
     * @return
     */
    public int addUser(User user) {

        //查询角色
        Role role = roleMapper.findById(user.getRoleId());
        if (CommonUtil.isNotEmpty(role)) {
            user.setRoleName(role.getRoleName());
        }
        //判断是否存在
        User bean = userMapper.findByUserName(user.getUserName());
        if(CommonUtil.isNotEmpty(bean)) {
            throw new WasBackastageException(ResultEnum.SAME_USER);
        }
        //这里设置初始密码
        String password = MD5Util.getMD5(user.getUserName()+MD5Util.getMD5("123456"));
        user.setPassword(password);
        user.setCreateTime(DatesUtils.time());
        user.setUpdateTime(DatesUtils.time());
        user.setLoginTime(DatesUtils.time());
        user.setStatus(0);
        user.setVersion(1l);
        return userMapper.addUser(user);
    }
}

package com.fq.fqltdwasbackstage.service;

import com.alibaba.fastjson.JSONObject;
import com.fq.fqltdwasbackstage.domain.Menu;
import com.fq.fqltdwasbackstage.domain.MenuRole;
import com.fq.fqltdwasbackstage.domain.Role;
import com.fq.fqltdwasbackstage.domain.User;
import com.fq.fqltdwasbackstage.enums.ResultEnum;
import com.fq.fqltdwasbackstage.exception.WasBackastageException;
import com.fq.fqltdwasbackstage.mapper.MenuMapper;
import com.fq.fqltdwasbackstage.mapper.RoleMapper;
import com.fq.fqltdwasbackstage.mapper.UserMapper;
import com.fq.fqltdwasbackstage.utils.CommonUtil;
import com.fq.fqltdwasbackstage.utils.DatesUtils;
import com.fq.fqltdwasbackstage.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Descript:
 * @Author: liuyingjie
 * @Date: create in 2018/5/24 0024 16:13
 */
@Service
@Slf4j
public class LoginService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 账户密码加密规则   MD5(账号+MD5(密码))
     * @param userName
     * @param password
     * @return
     */
    public Map<String,Object> findUser(String userName, String password) {

        //加密
        password = MD5Util.getMD5(userName+password);

        User user = mapper.findByUserNameAndPassword(userName,password);
        Map<String,Object> map = new HashMap<>();
        if(mapper.findByUserName(userName) != null){
            if(user != null){
                if (user.getStatus() == 0) {
                    map.put("user",user);
                    //获取权限信息
                    Long roleId = user.getRoleId();
                    //判断角色是否可用
                    Role role = roleMapper.findById(roleId);
                    if (CommonUtil.isNotEmpty(role)) {
                        if (role.getStatus() == 1) {
                            throw new WasBackastageException(ResultEnum.ROLE_DISABLED);
                        }
                    }
                    //修改登录时间
                    user.setLoginTime(DatesUtils.time());
                    mapper.optUpdateUser(user);

                    return map;
                } else {
                    throw new WasBackastageException(ResultEnum.USER_DISABLED);
                }

            }else{
                throw new WasBackastageException(ResultEnum.ERROR_PASSWORD);
            }
        }else{
            throw  new WasBackastageException(ResultEnum.UNKNOW_ACCOUNT);
        }
    }

    /**
     * 校验登陆
     * @param sessionId
     * @return
     */
//    public boolean check(String sessionId) {
//
//        boolean flag = stringRedisTemplate.hasKey(sessionId);
//        if(flag){
//            //重新设置session有效期
//            String str = stringRedisTemplate.opsForValue().get(sessionId);
//            stringRedisTemplate.opsForValue().set(sessionId, str,1800, TimeUnit.SECONDS);
//        }else{
//            throw new WasBackastageException(ResultEnum.NOT_LOGIN);
//        }
//        return flag;
//    }

    /**
     * 退出登录
     * @param sessionId
     * @return
     */
    public boolean loginOut(String sessionId) {

        return true;
    }

    /**
     * \校验权限,后期权限写入数据库
     * @param user
     * @return
     */
    public boolean checkPermission(User user,String url) {

        log.info(url);//http://localhost:8081/consume/findConsume
        //检验权限
        String[] str = url.split("/");
        String checkUrl = str[str.length-2]+"/"+str[str.length-1];
        System.out.println(checkUrl);

        //先判断权限存在不.然后判断是否拥有权限
        Menu menu = menuMapper.findMenuByUrl(checkUrl);
        if (CommonUtil.isNotEmpty(menu)) {
            MenuRole menuRole = menuMapper.findInfoByRoleAndMenu(Long.parseLong(user.getRoleId().toString()),menu.getGid());
            if (CommonUtil.isNotEmpty(menuRole)) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
}

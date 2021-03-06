package com.fq.fqltdwasbackstage.mapper;

import com.fq.fqltdwasbackstage.domain.User;
import com.fq.fqltdwasbackstage.provider.BaseProvider;
import com.fq.fqltdwasbackstage.utils.SimpleInsertLangDriver;
import com.fq.fqltdwasbackstage.utils.SimpleUpdateLangDriver;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Descript:
 * @Author: liuyingjie
 * @Date: create in 2018/5/24 0024 16:50
 */
@Component
@Mapper
public interface UserMapper {

    /**
     * 根据登录名查询
     * @param userName
     * @return
     */
    @Select("select gid,user_name userName,password,tel,email,role_id roleId,role_name roleName,create_time createTime,update_time updateTime,login_time loginTime,status,version from sys_user where user_name = #{userName}")
    User findByUserName(String userName);

    /**
     * 账号密码查询
     * @param userName
     * @param password
     * @return
     */
    @Select("select gid,user_name userName,password,tel,email,role_id roleId,role_name roleName,create_time createTime,update_time updateTime,login_time loginTime,status,version from sys_user where user_name = #{userName} AND password = #{password}")
    User findByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);

    /**
     * 查询所有用户
     * @return
     */
    @SelectProvider(type = BaseProvider.class,method = "findAllUser")
    List<User> findAllUser(String userName);

    /**
     * 修改信息
     * @param user
     */
    @Update("update sys_user (#{user}) where gid = #{gid}")
    @Lang(SimpleUpdateLangDriver.class)
    boolean optUpdateUser(User user);

    /**
     * 删除用户
     * @param gid
     */
    @Delete("delete from sys_user where gid = #{gid}")
    int deleteUser(@Param("gid") Long gid);

    /**
     * 新增用户
     * @param user
     * @return
     */
    @Insert("insert into sys_user (#{user})")
    @Lang(SimpleInsertLangDriver.class)
    int addUser(User user);
}

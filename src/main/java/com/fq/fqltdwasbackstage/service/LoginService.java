package com.fq.fqltdwasbackstage.service;

import com.fq.fqltdwasbackstage.domain.User;

import java.util.*;

/**
 * @Descript:
 * @Author: liuyingjie
 * @Date: create in 2018/5/24 0024 16:13
 */
public interface LoginService {

    Map<String,Object> findUser(String userName, String password);

    boolean loginOut(String sessionId);

    boolean checkPermission(User user,String url);
}

package com.fq.fqltdwasbackstage.controller;

import com.fq.fqltdwasbackstage.domain.User;
import com.fq.fqltdwasbackstage.domain.common.Result;
import com.fq.fqltdwasbackstage.service.LoginService;
import com.fq.fqltdwasbackstage.utils.CommonUtil;
import com.fq.fqltdwasbackstage.utils.MD5Util;
import com.fq.fqltdwasbackstage.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private LoginService service;

    /**
     * 登陆
     * @param userName
     * @param password
     * @return
     */
    @PostMapping(value = "/signIn")
    public Result login(HttpServletRequest request, @RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password){

        //校验登陆
        Map<String,Object> map = service.findUser(userName, MD5Util.getMD5(password));
        HttpSession session = request.getSession();
        User user = (User)map.get("user");
        if(CommonUtil.isNotEmpty(user)){
            session.setAttribute("user",user);
            return ResultUtil.success(map,null);
        }else{
            return null;
        }
    }

    /**
     * 退出登陆
     * @param sessionId
     * @return
     */
    @GetMapping(value = "/signOut")
    public Result loginOut(String sessionId){


        return ResultUtil.success(service.loginOut(sessionId),null);

    }
}

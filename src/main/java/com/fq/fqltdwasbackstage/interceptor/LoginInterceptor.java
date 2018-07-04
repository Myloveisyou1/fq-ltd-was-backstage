package com.fq.fqltdwasbackstage.interceptor;

import com.fq.fqltdwasbackstage.domain.User;
import com.fq.fqltdwasbackstage.enums.ResultEnum;
import com.fq.fqltdwasbackstage.exception.WasBackastageException;
import com.fq.fqltdwasbackstage.service.LoginService;
import com.fq.fqltdwasbackstage.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Descript: 登陆拦截器
 * @Author: liuyingjie
 * @Date: create in 2017/12/28 0028 15:52
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginService service;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        log.info("===========进入拦截器==============");

        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        if (CommonUtil.isNotEmpty(user)) {
            String url = httpServletRequest.getRequestURL().toString();
            //校验是否有用删除权限
            if(service.checkPermission(user,url)) {
                session.setAttribute("user",user);
                return true;
            } else {
                throw new WasBackastageException(ResultEnum.NO_PERMISSION);
            }
        } else {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/login");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

        log.info(">>>MyInterceptor1>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");

        if(httpServletResponse.getStatus()==500){
//            modelAndView.setViewName("/errorpage/500");
            throw new WasBackastageException(ResultEnum.UNKNOW_ERROR);
        }else if(httpServletResponse.getStatus()==404){
//            modelAndView.setViewName("/errorpage/404");
            throw new WasBackastageException(ResultEnum.ERROR_PATH);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

package com.fq.fqltdwasbackstage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    /**
     * 跳转到登陆页面
     * @return
     */
    @GetMapping(value = "/login")
    public String login() {
        return "/login";
    }

    /**
     * 跳转到桌面
     * @return
     */
    @GetMapping(value = "/welcome")
    public String welcome() {
        return "/welcome";
    }

    /**
     * 跳转到首页
     * @return
     */
    @GetMapping(value = "/home")
    public String home() {
        return "/home";
    }

    /**
     * 加载顶部
     * @return
     */
    @GetMapping(value = "/common/top")
    public String top() {
        return "/common/top";
    }

    /**
     * 加载左边
     * @return
     */
    @GetMapping(value = "/common/left")
    public String left() {
        return "/common/left";
    }

    /**
     * 加载底部
     * @return
     */
    @GetMapping(value = "/common/footer")
    public String footer() {
        return "/common/footer";
    }

    /**
     * 数字货币管理
     * @return
     */
    @GetMapping(value = "/number-coin")
    public String numberCoin() {
        return "/number-coin";
    }

    @GetMapping(value = "/number-coin-add")
    public String numberCoinAdd() {
        return "/number-coin-add";
    }

    /**
     * 数字货币监控及预警
     * @return
     */
    @GetMapping(value = "/control-waring")
    public String controlWaring() {
        return "/control-waring";
    }

    /**
     * 地址池明细
     * @return
     */
    @GetMapping(value = "/address-pool")
    public String addressPool() {
        return "/address-pool";
    }

    /**
     * 充值到账监控
     * @return
     */
    @GetMapping(value = "/recharge-control")
    public String rechargeControl() {
        return "/recharge-control";
    }

    /**
     * 平台提币管理
     * @return
     */
    @GetMapping(value = "/withdraw-coin")
    public String withdrawCoin() {
        return "/withdraw-coin";
    }

    /**
     * 404
     * @return
     */
    @GetMapping(value = "/errorpage/404")
    public String notFound() {
        return "/errorpage/404";
    }

    /**
     * 500
     * @return
     */
    @GetMapping(value = "/errorpage/500")
    public String error() {
        return "/errorpage/500";
    }



    /**系统设置**/
    /**
     * 用户
     * @return
     */
    @GetMapping(value = "/user")
    public String user() {
        return "/user";
    }

    @GetMapping(value = "/user-add")
    public String userAdd() {
        return "/user-add";
    }

    @GetMapping(value = "/user-info")
    public String userInfo() {
        return "/user-info";
    }

    /**
     * 角色
     * @return
     */
    @GetMapping(value = "/role")
    public String role() {
        return "/role";
    }

    @GetMapping(value = "/role-add")
    public String roleAdd() {
        return "/role-add";
    }


    /**
     * 菜单
     * @return
     */
    @GetMapping(value = "/menu")
    public String menu() {
        return "/menu";
    }

    /**
     * 图标
     * @return
     */
    @GetMapping(value = "/icon")
    public String icon() {
        return "/icon";
    }
    /**
     * 代码生成器
     * @return
     */
    @GetMapping(value = "/generator")
    public String generator() {
        return "/generator";
    }
}

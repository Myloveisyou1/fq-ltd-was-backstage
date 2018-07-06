package com.fq.fqltdwasbackstage.controller;

import java.util.HashMap;
import java.util.Map;

import com.fq.fqltdwasbackstage.domain.SysConfig;
import com.fq.fqltdwasbackstage.domain.common.Pages;
import com.fq.fqltdwasbackstage.domain.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fq.fqltdwasbackstage.service.SysConfigService;
import com.fq.fqltdwasbackstage.utils.ResultUtil;

/**
 * 
 *
 * @author liuyingjie
 * @email liuyingjie@fengqun.ltd
 * @date 2018-07-06 16:54:47
 */
@RestController
@RequestMapping("v1/sysconfig")
public class SysConfigController {

    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(SysConfig sysConfig, Pages pages){

        Map<String,Object> params = new HashMap<>();
        params.put("bean",sysConfig);
        params.put("pages",pages);
        Map<String,Object> map = sysConfigService.queryPage(params);

        return ResultUtil.success(map.get("result"),(Pages)map.get("pages"));
    }

}

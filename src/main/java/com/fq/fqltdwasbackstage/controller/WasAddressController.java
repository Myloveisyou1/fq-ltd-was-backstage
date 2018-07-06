package com.fq.fqltdwasbackstage.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fq.fqltdwasbackstage.domain.WasAddress;
import com.fq.fqltdwasbackstage.domain.common.Pages;
import com.fq.fqltdwasbackstage.domain.common.Result;
import com.fq.fqltdwasbackstage.service.WasAddressService;
import com.fq.fqltdwasbackstage.utils.ResultUtil;

/**
 * 钱包地址到账情况表
 *
 * @author liuyingjie
 * @email liuyingjie@fengqun.ltd
 * @date 2018-07-06 16:05:19
 */
@RestController
@RequestMapping("fqltdwasbackstage/wasaddress")
public class WasAddressController {

    @Autowired
    private WasAddressService wasAddressService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(WasAddress wasAddress, Pages pages){

        Map<String,Object> params = new HashMap<>();
        params.put("bean",wasAddress);
        params.put("pages",pages);
        Map<String,Object> map = wasAddressService.queryPage(params);

        return ResultUtil.success(map.get("result"),(Pages)map.get("pages"));
    }


    /**
     * 查询单条信息
     */
    @RequestMapping("/info/{wasId}")
    public Result info(@PathVariable("wasId") Long wasId){

		WasAddress wasAddress = wasAddressService.selectById(wasId);
        return ResultUtil.success(wasAddress,null);
    }

    /**
     * 保存信息
     */
    @RequestMapping("/save")
    public Result save(WasAddress wasAddress){

        return ResultUtil.success(wasAddressService.insert(wasAddress),null);
    }

    /**
     * 修改信息
     */
    @RequestMapping("/update")
    public Result update(WasAddress wasAddress){

        return ResultUtil.success(wasAddressService.updateById(wasAddress),null);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Long[] wasIds){
			wasAddressService.deleteBatchIds(Arrays.asList(wasIds));

        return ResultUtil.success(wasAddressService.deleteBatchIds(Arrays.asList(wasIds)),null);
    }

}

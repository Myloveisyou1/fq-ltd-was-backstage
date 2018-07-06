package com.fq.fqltdwasbackstage.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fq.fqltdwasbackstage.domain.WasAddressList;
import com.fq.fqltdwasbackstage.domain.common.Pages;
import com.fq.fqltdwasbackstage.domain.common.Result;
import com.fq.fqltdwasbackstage.service.WasAddressListService;
import com.fq.fqltdwasbackstage.utils.ResultUtil;




/**
 * 钱包地址列表（运维生成）
 *
 * @author liuyingjie
 * @email liuyingjie@fengqun.ltd
 * @date 2018-07-06 16:05:19
 */
@RestController
@RequestMapping("v1/wasaddresslist")
public class WasAddressListController {

    @Autowired
    private WasAddressListService wasAddressListService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(WasAddressList wasAddressList, Pages pages){

        Map<String,Object> params = new HashMap<>();
        params.put("bean",wasAddressList);
        params.put("pages",pages);
        Map<String,Object> map = wasAddressListService.queryPage(params);

        return ResultUtil.success(map.get("result"),(Pages)map.get("pages"));
    }


    /**
     * 查询单条信息
     */
    @RequestMapping("/info/{wasId}")
    public Result info(@PathVariable("wasId") Integer wasId){

		WasAddressList wasAddressList = wasAddressListService.selectById(wasId);
        return ResultUtil.success(wasAddressList,null);
    }

    /**
     * 保存信息
     */
    @RequestMapping("/save")
    public Result save(WasAddressList wasAddressList){

        return ResultUtil.success(wasAddressListService.insert(wasAddressList),null);
    }

    /**
     * 修改信息
     */
    @RequestMapping("/update")
    public Result update(WasAddressList wasAddressList){

        return ResultUtil.success(wasAddressListService.updateById(wasAddressList),null);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Integer[] wasIds){
			wasAddressListService.deleteBatchIds(Arrays.asList(wasIds));

        return ResultUtil.success(wasAddressListService.deleteBatchIds(Arrays.asList(wasIds)),null);
    }

}

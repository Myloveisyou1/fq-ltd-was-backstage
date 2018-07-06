package com.fq.fqltdwasbackstage.service;

import com.fq.fqltdwasbackstage.domain.WasAddress;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 钱包地址到账情况表
 *
 * @author liuyingjie
 * @email liuyingjie@fengqun.ltd
 * @date 2018-07-06 16:05:19
 */
public interface  WasAddressService {

    Map<String,Object> queryPage(Map<String, Object> params);

    WasAddress selectById(Long wasId);

    boolean insert(WasAddress wasAddress);

    boolean updateById(WasAddress wasAddress);

    boolean deleteBatchIds(List<Long> longs);
}


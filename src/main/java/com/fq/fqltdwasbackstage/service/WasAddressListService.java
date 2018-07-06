package com.fq.fqltdwasbackstage.service;

import com.fq.fqltdwasbackstage.domain.WasAddressList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 钱包地址列表（运维生成）
 *
 * @author liuyingjie
 * @email liuyingjie@fengqun.ltd
 * @date 2018-07-06 16:05:19
 */
public interface  WasAddressListService {

    Map<String,Object> queryPage(Map<String, Object> params);

    WasAddressList selectById(Integer wasId);

    boolean insert(WasAddressList wasAddressList);

    boolean updateById(WasAddressList wasAddressList);

    boolean deleteBatchIds(List<Integer> integers);
}


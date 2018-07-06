package com.fq.fqltdwasbackstage.service.impl;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fq.fqltdwasbackstage.domain.WasAddressList;
import com.fq.fqltdwasbackstage.service.WasAddressListService;
import com.fq.fqltdwasbackstage.domain.common.Pages;
import com.fq.fqltdwasbackstage.mapper.WasAddressListMapper;
import org.springframework.beans.factory.annotation.Autowired;


@Service("wasAddressListService")
public class WasAddressListServiceImpl implements WasAddressListService {

    @Autowired
    private WasAddressListMapper wasAddressListMapper;

    /**
    * 分页查询数据
    * @param params
    * @return
    */
    public Map<String,Object> queryPage(Map<String, Object> params) {

        Map<String,Object> map = new HashMap<>();

        //这里开始写具体的业务逻辑
        WasAddressList bean = (WasAddressList)params.get("bean");
        Pages pages = (Pages) params.get("pages");

        List<WasAddressList> list = wasAddressListMapper.pageQuery(bean,pages);
        Long count = wasAddressListMapper.pageQueryCount(bean,pages);
        pages.setTotalCount(count);
        long totalPage = count%pages.getPageSize()>0?(1+count/pages.getPageSize()):(count/pages.getPageSize());
        pages.setTotalPage(Integer.valueOf(totalPage+""));

        map.put("result",list);//数据信息
        map.put("pages",pages);//分页信息

        return map;
    }

    /**
     * 根据id查询信息
     * @param wasId 主键id
     */
    public WasAddressList selectById(Integer wasId) {

        WasAddressList wasAddressList = wasAddressListMapper.selectById(wasId);

        return wasAddressList;
    }

    /**
     * 保存信息
     * @param wasAddressList 需要保存的对象
     * @return
     */
    public boolean insert(WasAddressList wasAddressList) {

        return wasAddressListMapper.insert(wasAddressList);
    }

    /**
     * 修改信息
     * @param wasAddressList 需要修改的对象
     * @return
     */
    public boolean updateById(WasAddressList wasAddressList) {

        return wasAddressListMapper.updateById(wasAddressList);
    }

    @Override
    public boolean deleteBatchIds(List<Integer> integers) {
        return false;
    }

}

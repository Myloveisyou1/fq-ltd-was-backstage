package com.fq.fqltdwasbackstage.mapper;

import com.fq.fqltdwasbackstage.domain.WasAddressList;
import com.fq.fqltdwasbackstage.domain.common.Pages;
import com.fq.fqltdwasbackstage.provider.BaseProvider;
import com.fq.fqltdwasbackstage.utils.SimpleInsertLangDriver;
import com.fq.fqltdwasbackstage.utils.SimpleUpdateLangDriver;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 钱包地址列表（运维生成）
 * 
 * @author liuyingjie
 * @email liuyingjie@fengqun.ltd
 * @date 2018-07-06 16:05:19
 */
@Component
@Mapper
public interface WasAddressListMapper {

    @Select("select "
                +"was_id wasId,"
                +"was_type wasType,"
                +"was_address wasAddress,"
                +"was_expire_time wasExpireTime,"
                +"was_source wasSource,"
                +"was_remark wasRemark,"
                +" from was_address_list where was_id=#{wasId}")
    WasAddressList selectById(Integer wasId);

    @Insert("insert into was_address_list (#{wasAddressList})")
    @Lang(SimpleInsertLangDriver.class)
    @Options(useGeneratedKeys = true,keyProperty = "wasId",keyColumn = "was_id")
    boolean insert(WasAddressList wasAddressList);

    @Update("update was_address_list (#{wasAddressList}) where was_id = #{wasId}")
    @Lang(SimpleUpdateLangDriver.class)
    boolean updateById(WasAddressList wasAddressList);

    @SelectProvider(type = BaseProvider.class,method = "pageQueryWasAddressListList")
    List<WasAddressList> pageQuery(WasAddressList wasAddressList, Pages pages);

    @SelectProvider(type = BaseProvider.class,method = "pageQueryWasAddressListCount")
    Long pageQueryCount(WasAddressList wasAddressList, Pages pages);
}

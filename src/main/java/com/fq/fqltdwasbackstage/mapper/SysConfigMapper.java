package com.fq.fqltdwasbackstage.mapper;

import com.fq.fqltdwasbackstage.domain.SysConfig;
import com.fq.fqltdwasbackstage.domain.common.Pages;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 
 * 
 * @author liuyingjie
 * @email liuyingjie@fengqun.ltd
 * @date 2018-07-06 16:54:47
 */
@Mapper
public interface SysConfigMapper {

    List<SysConfig> pageQueryList(SysConfig sysConfig, Pages pages);
}

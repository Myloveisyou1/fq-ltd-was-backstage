package com.fq.fqltdwasbackstage.domain;

import lombok.Data;

/**
 * @Descript:  角色实体
 * @Author: liuyingjie
 * @Date: create in 2018/5/29 0029 17:35
 */
@Data
public class Role{

    private Long gid;

    private String roleName;

    private String roleNameEn;

    private Long version;

    private String createTime;

    private String updateTime;

    private Integer status;
}

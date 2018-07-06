package com.fq.fqltdwasbackstage.service;

import com.fq.fqltdwasbackstage.domain.Icon;
import com.fq.fqltdwasbackstage.domain.Menu;
import com.fq.fqltdwasbackstage.domain.MenuList;
import com.fq.fqltdwasbackstage.enums.ResultEnum;
import com.fq.fqltdwasbackstage.exception.WasBackastageException;
import com.fq.fqltdwasbackstage.mapper.MenuMapper;
import com.fq.fqltdwasbackstage.utils.BaseUtils;
import com.fq.fqltdwasbackstage.utils.CommonUtil;
import com.fq.fqltdwasbackstage.utils.DatesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Descript:
 * @Author: liuyingjie
 * @Date: create in 2018/5/31 0031 10:32
 */
public interface MenuService {

    List<Menu> findAllMenu();

    List<Icon> findIconList();

    boolean addIcon(String name,String url);

    boolean addMenu(Menu menu);

    boolean delMenu(Long gid);

    Menu findById(Long gid);

    boolean editMenu(Menu menu);

    List<Menu> findChildMenu(String parentCode);

    List<MenuList> findMenuByRole(Long roleId);
}

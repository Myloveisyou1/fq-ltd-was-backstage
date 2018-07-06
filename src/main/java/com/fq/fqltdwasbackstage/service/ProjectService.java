package com.fq.fqltdwasbackstage.service;

import com.fq.fqltdwasbackstage.domain.Menu;
import com.fq.fqltdwasbackstage.domain.Project;
import com.fq.fqltdwasbackstage.mapper.MenuMapper;
import com.fq.fqltdwasbackstage.mapper.ProjectMapper;
import com.fq.fqltdwasbackstage.utils.CommonUtil;
import com.fq.fqltdwasbackstage.utils.DatesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Descript:
 * @Author: liuyingjie
 * @Date: create in 2018/6/1 0001 10:36
 */
public interface ProjectService {

    List<Project> findAllProject(String projectName);

    boolean updateProject(Project project);

    boolean deleteProject(Long gid);

    int addProject(String projectName, String nameEn);

}

package com.fq.fqltdwasbackstage.service.impl;

import com.fq.fqltdwasbackstage.domain.Menu;
import com.fq.fqltdwasbackstage.domain.Project;
import com.fq.fqltdwasbackstage.mapper.MenuMapper;
import com.fq.fqltdwasbackstage.mapper.ProjectMapper;
import com.fq.fqltdwasbackstage.service.ProjectService;
import com.fq.fqltdwasbackstage.utils.CommonUtil;
import com.fq.fqltdwasbackstage.utils.DatesUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper mapper;
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 查询所有项目
     * @param projectName
     * @return
     */
    public List<Project> findAllProject(String projectName) {

        if (CommonUtil.isNotEmpty(projectName)) {
            projectName = "%"+projectName+"%";
            return mapper.findProjectByName(projectName);
        } else {
            return mapper.findAllProject();
        }
    }

    /**
     * 修改信息
     * @param project
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean updateProject(Project project) {

        Project bean = new Project();
        if (CommonUtil.isNotEmpty(project)) {
            bean = mapper.findById(project.getGid());
            if (CommonUtil.isNotEmpty(bean)) {
                if (CommonUtil.isNotEmpty(project.getProjectName())) {
                    bean.setProjectName(project.getProjectName());
                }
                if (CommonUtil.isNotEmpty(project.getNameEn())) {
                    bean.setNameEn(project.getNameEn());
                }
                if (CommonUtil.isNotEmpty(project.getStatus())) {
                    //在这里如果禁用\启用项目,那么对应的菜单权限也应该禁用\启用
                    Menu menu = new Menu();
                    menu.setBelong(bean.getGid());
                    menu.setStatus(project.getStatus());
                    menuMapper.updateMenuByProject(menu);
                    bean.setStatus(project.getStatus());
                }
                bean.setUpdateTime(DatesUtils.time());
                bean.setVersion(bean.getVersion()+1);
            }
        }

        return mapper.updateProject(bean);
    }

    /**
     * 删除项目
     * @param gid
     * @return
     */
    public boolean deleteProject(Long gid) {

        //删除菜单
        boolean flag = menuMapper.deleteMenuByProject(gid);

        //删除项目
        mapper.deleteProjectById(gid);

        return flag;

    }

    /**
     * 添加项目
     * @param projectName
     * @param nameEn
     * @return
     */
    public int addProject(String projectName, String nameEn) {

        Project project = new Project();

        if (CommonUtil.isNotEmpty(projectName)) {
            project.setProjectName(projectName);
        }
        if (CommonUtil.isNotEmpty(nameEn)) {
            project.setNameEn(nameEn);
        }
        project.setVersion(1l);
        project.setStatus(0);
        project.setCreateTime(DatesUtils.time());
        project.setUpdateTime(DatesUtils.time());

        return mapper.addProject(project);
    }
}

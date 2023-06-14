package com.sisp.service;

import com.sisp.common.utils.UUIDUtil;
import com.sisp.dao.ProjectEntityMapper;
import com.sisp.dao.entity.ProjectEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectEntityMapper projectEntityMapper;

    /**
     * 查询项目列表
     */
    public List<ProjectEntity> queryProjectList(ProjectEntity projectEntity) {
        return projectEntityMapper.queryProjectList(projectEntity);
    }

    /**
     * 增加项目
     */
    public int addProjectInfo(ProjectEntity projectEntity) {
        int result;
        projectEntity.setId(UUIDUtil.getOneUUID());
        try {
            result = projectEntityMapper.insert(projectEntity);
        } catch ( Exception e ) {
            return 0;
        }
        return result;
    }

    /**
     * 修改项目
     */
    public int modifyProjectInfo(ProjectEntity projectEntity) {
        int result;
        try {
            result = projectEntityMapper.updateByPrimaryKeySelective(projectEntity);
        } catch ( Exception e ) {
            return 0;
        }
        return result;
    }

    /**
     * 删除项目
     */
    public int deleteProjectById(ProjectEntity projectEntity) {
        return projectEntityMapper.deleteProjectById(projectEntity);
    }


}

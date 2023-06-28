package com.sisp.service;

import com.sisp.common.utils.UUIDUtil;
import com.sisp.dao.ProjectEntityMapper;
import com.sisp.dao.entity.ProjectEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
        projectEntity.setId(UUIDUtil.getOneUUID());
        try {
            projectEntityMapper.insert(projectEntity);
        } catch ( Exception e ) {
            return 0;
        }
        return 1;
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

    /**
     * 搜索项目，并附带所有问卷信息
     */
    public List<Map<String,Object>> queryProjectQuestionnaire(ProjectEntity project) {
        return projectEntityMapper.queryProjectQuestionnaire(project);
    }

    /**
     * 查询问卷回答信息
     */
    public List<Map<String,Object>> queryQuestionnaireAnswers(String projectId, String respondent) {
        return projectEntityMapper.queryQuestionnaireAnswers(projectId, respondent);
    }

    /**
     * 获取不同问卷相同问题的回答
     */
    public List<Map<String,Object>> querySameQuestionStat(String projectId, String questionName) {
        return projectEntityMapper.querySameQuestionStat(projectId,questionName);
    }
}

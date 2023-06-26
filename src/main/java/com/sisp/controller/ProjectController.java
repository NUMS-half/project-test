package com.sisp.controller;

import com.sisp.beans.HttpResponseEntity;
import com.sisp.dao.entity.ProjectEntity;
import com.sisp.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    /**
     * 项目列表
     */
    @PostMapping(value = "/queryProjectList", headers = "Accept=application/json")
    public HttpResponseEntity queryProjectList(@RequestBody ProjectEntity project) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            List<ProjectEntity> hasProject = projectService.queryProjectList(project);
            if ( CollectionUtils.isEmpty(hasProject) ) {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(null);
                httpResponseEntity.setMessage("无项目信息");
            } else {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasProject);
                httpResponseEntity.setMessage("查询成功");
            }
        } catch ( Exception e ) {
            httpResponseEntity.setCode("-1");
            httpResponseEntity.setData(0);
            httpResponseEntity.setMessage("查询时发生异常，请稍后重试！");
        }
        return httpResponseEntity;
    }

    /**
     * 新增项目
     */
    @PostMapping(value = "/addProjectInfo", headers = "Accept=application/json")
    public HttpResponseEntity addProjectInfo(@RequestBody ProjectEntity project) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        int result = projectService.addProjectInfo(project);
        if ( result != 0 ) {
            httpResponseEntity.setCode("666");
            httpResponseEntity.setData(result);
            httpResponseEntity.setMessage("创建成功");
        } else {
            httpResponseEntity.setCode("0");
            httpResponseEntity.setData(0);
            httpResponseEntity.setMessage("创建失败,请检查输入是否合法！");
        }
        return httpResponseEntity;
    }

    /**
     * 修改项目
     */
    @PostMapping(value = "/modifyProjectInfo", headers = "Accept=application/json")
    public HttpResponseEntity modifyProjectInfo(@RequestBody ProjectEntity project) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        int result = projectService.modifyProjectInfo(project);
        if ( result != 0 ) {
            httpResponseEntity.setCode("666");
            httpResponseEntity.setData(result);
            httpResponseEntity.setMessage("编辑成功");
        } else {
            httpResponseEntity.setCode("0");
            httpResponseEntity.setData(0);
            httpResponseEntity.setMessage("编辑失败,请检查您的输入是否合法！");
        }

        return httpResponseEntity;
    }

    /**
     * 删除项目
     */
    @PostMapping(value = "/deleteProjectById", headers = "Accept=application/json")
    public HttpResponseEntity deleteProjectById(@RequestBody ProjectEntity project) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            if ( project == null ) {
                throw new NullPointerException();
            }
            int result = projectService.deleteProjectById(project);
            if ( result != 0 ) {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("删除成功");
            } else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("删除失败");
            }
        } catch ( Exception e ) {
            httpResponseEntity.setCode("-1");
            httpResponseEntity.setData(0);
            httpResponseEntity.setMessage("删除时发生异常，请稍后重试！");
        }
        return httpResponseEntity;
    }

    /**
     * 搜索项目，并附带所有问卷信息
     */
    @PostMapping(value = "/queryProjectQuestionnaire", headers = "Accept=application/json")
    public HttpResponseEntity queryProjectQuestionnaire(@RequestBody ProjectEntity project) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            List<Map<String, Object>> projectQuestionnaire = projectService.queryProjectQuestionnaire(project);
            if ( CollectionUtils.isEmpty(projectQuestionnaire) ) {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(null);
                httpResponseEntity.setMessage("该项目暂无问卷信息");
            } else {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(projectQuestionnaire);
                httpResponseEntity.setMessage("查询成功");
            }
        } catch ( Exception e ) {
            httpResponseEntity.setCode("-1");
            httpResponseEntity.setData(0);
            httpResponseEntity.setMessage("查询时发生异常，请稍后重试！");
        }
        return httpResponseEntity;
    }

    /**
     * 获取问卷回答信息
     */
    @PostMapping(value = "/queryQuestionnaireRespondent", headers = "Accept=application/json")
    public HttpResponseEntity queryQuestionnaireRespondent(@RequestBody ProjectEntity project) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            List<Map<String, Object>> questionnaireAnswers = projectService.queryQuestionnaireAnswers(project);
            if ( CollectionUtils.isEmpty(questionnaireAnswers) ) {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(null);
                httpResponseEntity.setMessage("该项目暂无问卷的作答信息");
            } else {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(questionnaireAnswers);
                httpResponseEntity.setMessage("查询成功");
            }
        } catch ( Exception e ) {
            httpResponseEntity.setCode("-1");
            httpResponseEntity.setData(0);
            httpResponseEntity.setMessage("查询时发生异常，请稍后重试！");
        }

        return httpResponseEntity;
    }
}

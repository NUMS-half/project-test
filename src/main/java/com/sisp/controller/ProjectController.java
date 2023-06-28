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

    private static final HttpResponseEntity successAdd;
    private static final HttpResponseEntity failureAdd;
    private static final HttpResponseEntity successDel;
    private static final HttpResponseEntity failureDel;

    static {
        successAdd = new HttpResponseEntity();
        successAdd.setCode("666");
        successAdd.setData(1);
        successAdd.setMessage("创建成功");

        failureAdd = new HttpResponseEntity();
        failureAdd.setCode("0");
        failureAdd.setData(0);
        failureAdd.setMessage("创建失败");

        successDel = new HttpResponseEntity();
        successDel.setCode("666");
        successDel.setData(1);
        successDel.setMessage("删除成功");

        failureDel = new HttpResponseEntity();
        failureDel.setCode("0");
        failureDel.setData(0);
        failureDel.setMessage("删除失败");
    }

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
        if ( projectService.addProjectInfo(project) != 0 ) {
            return successAdd;
        } else {
            return failureAdd;
        }
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
        try {
            if ( project == null ) {
                throw new NullPointerException();
            }
            int result = projectService.deleteProjectById(project);
            if ( result != 0 ) {
                return successDel;
            } else {
                return failureDel;
            }
        } catch ( Exception e ) {
            return failureDel;
        }
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
    public HttpResponseEntity queryQuestionnaireRespondent(@RequestBody Map<String, Object> queryInfo) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            List<Map<String, Object>> questionnaireAnswers = projectService.queryQuestionnaireAnswers((String) queryInfo.get("projectId"), (String) queryInfo.get("respondent"));
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

    /**
     * 获取相同问题在不同问卷中的回答
     */
    @PostMapping(value = "/querySameQuestionStatistic", headers = "Accept=application/json")
    public HttpResponseEntity querySameQuestionStatistic(@RequestBody Map<String, Object> map) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        String inputId = (String) map.get("projectId");
        String inputDescription = (String) map.get("questionDescription");
        try {
            List<Map<String, Object>> answerInfo = projectService.querySameQuestionStat(inputId, inputDescription);
            if ( CollectionUtils.isEmpty(answerInfo) || Integer.parseInt(answerInfo.get(0).get("option_count").toString()) == 0 ) {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(null);
                httpResponseEntity.setMessage("该问题暂无在其他问卷中的作答信息！");
            } else {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(answerInfo);
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

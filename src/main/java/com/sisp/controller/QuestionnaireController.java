package com.sisp.controller;

import com.sisp.beans.HttpResponseEntity;
import com.sisp.dao.entity.OptionEntity;
import com.sisp.dao.entity.QuestionEntity;
import com.sisp.dao.entity.QuestionnaireEntity;
import com.sisp.service.OptionService;
import com.sisp.service.QuestionService;
import com.sisp.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private OptionService optionService;

    @Autowired
    private HttpSession session;

    /**
     * 解析访问问卷请求
     */
    @GetMapping(value = "/questionnaireId/{id}")
    public void handleURL(@PathVariable String id, HttpServletResponse response) throws IOException {
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setId(id);
        List<QuestionnaireEntity> result = questionnaireService.queryQuestionnaireList(questionnaireEntity);

        if ( result.isEmpty() || result.get(0).getStatus() != 0 ) {
            response.sendRedirect("/pages/error/accessDenied.html");
        } else {
            session.setAttribute("id", id);
            response.sendRedirect("/pages/beginAnswer/index.html");
        }
    }

    @GetMapping("/getSessionData")
    public Map<String, Object> getSessionData() {
        Map<String, Object> sessionData = new HashMap<>();
        sessionData.put("questionnaireId", session.getAttribute("id"));
        session.invalidate();
        return sessionData;
    }

    /**
     * 查询问卷列表
     */
    @PostMapping(value = "/queryQuestionnaireList", headers = "Accept=application/json")
    public HttpResponseEntity queryQuestionnaireList(@RequestBody QuestionnaireEntity questionnaire) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            List<QuestionnaireEntity> questionnaireList = questionnaireService.queryQuestionnaireList(questionnaire);
            if ( CollectionUtils.isEmpty(questionnaireList) ) {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(null);
                httpResponseEntity.setMessage("无项目信息");
            } else {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(questionnaireList);
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
     * 读取目标问卷内容
     */
    @PostMapping(value = "/queryQuestionnaireInfo", headers = "Accept=application/json")
    public HttpResponseEntity queryQuestionnaireInfo(@RequestBody QuestionnaireEntity questionnaire) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        QuestionEntity question = new QuestionEntity();
        question.setQuestionnaireId(questionnaire.getId());

        List<QuestionnaireEntity> questionnaireList = questionnaireService.queryQuestionnaireList(questionnaire);
        if ( questionnaireList.isEmpty() ) {
            httpResponseEntity.setCode("0");
            httpResponseEntity.setData(null);
            httpResponseEntity.setMessage("无项目信息");
        } else {
            QuestionnaireEntity questionnaireEntity = questionnaireList.get(0);

            List<QuestionEntity> questionInfos = questionService.queryQuestion(question);
            List<Map<String, Object>> questionList = new ArrayList<>();

            for ( QuestionEntity q : questionInfos ) {
                OptionEntity option = new OptionEntity();
                option.setQuestionId(q.getId());
                List<Map<String, Object>> optionList = optionService.queryOption(option);

                Map<String, Object> map = new HashMap<>();
                map.put("problemName", q.getQuestionDescription());
                map.put("mustAnswer", q.isMustAnswer());
                map.put("type", q.getType());
                map.put("leftTitle", q.getLeftTitle());
                map.put("option", optionList);

                questionList.add(map);
            }
            Map<String, Object> params = new HashMap<>();
            params.put("id", questionnaireEntity.getId());
            params.put("questionnaireName", questionnaireEntity.getQuestionnaireName());
            params.put("questionnaireDescription", questionnaireEntity.getQuestionnaireDescription());
            params.put("questionList", questionList);

            httpResponseEntity.setCode("666");
            httpResponseEntity.setData(params);
            httpResponseEntity.setMessage("读取成功");
        }
        return httpResponseEntity;
    }

    /**
     * 新建问卷
     */
    @PostMapping(value = "/addQuestionnaireInfo", headers = "Accept=application/json")
    public HttpResponseEntity addQuestionnaireInfo(@RequestBody QuestionnaireEntity questionnaire) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        int result = questionnaireService.addQuestionnaireInfo(questionnaire);
        if ( result != 0 ) {
            httpResponseEntity.setCode("666");
            httpResponseEntity.setData(questionnaire.getId());
            httpResponseEntity.setMessage("创建成功");
        } else {
            httpResponseEntity.setCode("0");
            httpResponseEntity.setData(0);
            httpResponseEntity.setMessage("创建失败,请检查输入是否合法！");
        }
        return httpResponseEntity;
    }

    /**
     * 修改问卷
     */
    @PostMapping(value = "/modifyQuestionnaireInfo", headers = "Accept=application/json")
    public HttpResponseEntity modifyQuestionnaireInfo(@RequestBody Map<String, Object> params) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        String id = (String) params.get("id");
        String questionnaireName = (String) params.get("questionnaireName");
        String questionnaireDescription = (String) params.get("questionnaireDescription");

        QuestionnaireEntity questionnaire = new QuestionnaireEntity();
        questionnaire.setId(id);
        questionnaire.setQuestionnaireName(questionnaireName);
        questionnaire.setQuestionnaireDescription(questionnaireDescription);

        List<Map<String, Object>> questionList = (List<Map<String, Object>>) params.get("questionList");
        String[] questionNames = new String[questionList.size()];
        Boolean[] mustAnswers = new Boolean[questionList.size()];
        int[] types = new int[questionList.size()];
        String[] leftTitles = new String[questionList.size()];
        List<Map<String, Object>>[] questionOptions = new ArrayList[questionList.size()];

        int i = 0;
        for ( Map<String, Object> map : questionList ) {
            questionNames[i] = (String) map.get("problemName");
            mustAnswers[i] = (Boolean) map.get("mustAnswer");
            types[i] = (Integer) map.get("type");
            leftTitles[i] = (String) map.get("leftTitle");
            questionOptions[i] = (ArrayList) map.get("option");
            i++;
        }
        int result1 = questionnaireService.modifyQuestionnaireInfo(questionnaire);

        List<QuestionEntity> questionEntityList = new ArrayList<>();
        for ( int j = 0; j < questionList.size(); j++ ) {
            QuestionEntity question = new QuestionEntity();
            question.setQuestionnaireId(id);
            question.setQuestionDescription(questionNames[j]);
            question.setMustAnswer(mustAnswers[j]);
            question.setType(types[j]);
            question.setLeftTitle(leftTitles[j]);
            question.setQuestionIndex(j + 1);

            questionEntityList.add(question);
        }
        int result2 = questionService.insertBatch(questionEntityList);

        List<OptionEntity> optionEntityList = new ArrayList<>();
        for ( int j = 0; j < questionList.size(); j++ ) {
            for ( int k = 0; k < questionOptions[j].size(); k++ ) {
                OptionEntity option = new OptionEntity();
                option.setQuestionId(questionEntityList.get(j).getId());
                option.setChooseTerm((String) questionOptions[j].get(k).get("chooseTerm"));
                option.setFraction((String) questionOptions[j].get(k).get("fraction"));

                optionEntityList.add(option);
            }
        }
        int result3 = optionService.insertBatch(optionEntityList);

        if ( result1 != 0 && result2 != 0 && result3 != 0 ) {
            httpResponseEntity.setCode("666");
            httpResponseEntity.setData(id);
            httpResponseEntity.setMessage("编辑成功");
        } else {
            httpResponseEntity.setCode("0");
            httpResponseEntity.setData(0);
            httpResponseEntity.setMessage("编辑失败,请检查您的输入是否合法！");
        }
        return httpResponseEntity;
    }

    /**
     * 逻辑删除问卷
     */
    @PostMapping(value = "/deleteQuestionnaire", headers = "Accept=application/json")
    public HttpResponseEntity deleteQuestionnaire(@RequestBody QuestionnaireEntity questionnaire) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        questionnaire.setStatus(-1);
        int result = questionnaireService.modifyQuestionnaireInfo(questionnaire);
        if ( result != 0 ) {
            httpResponseEntity.setCode("666");
            httpResponseEntity.setData(questionnaire.getId());
            httpResponseEntity.setMessage("删除成功");
        } else {
            httpResponseEntity.setCode("0");
            httpResponseEntity.setData(0);
            httpResponseEntity.setMessage("删除失败");
        }
        return httpResponseEntity;
    }

    /**
     * 发布/收回问卷
     */
    @PostMapping(value = "/setQuestionnaireStatus", headers = "Accept=application/json")
    public HttpResponseEntity setQuestionnaireStatus(@RequestBody QuestionnaireEntity questionnaire) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        if (questionnaire.getStatus() == 1) {
            questionnaire.setPublishTime(null);
        }

        int result = questionnaireService.modifyQuestionnaireInfo(questionnaire);
        if ( result != 0 ) {
            httpResponseEntity.setCode("666");
            httpResponseEntity.setData(questionnaire.getId());
            httpResponseEntity.setMessage(questionnaire.getStatus() == 0 ? "发布成功" : "关闭成功");
        } else {
            httpResponseEntity.setCode("0");
            httpResponseEntity.setData(0);
            httpResponseEntity.setMessage(questionnaire.getStatus() == 0 ? "发布失败" : "关闭失败");
        }
        return httpResponseEntity;
    }
}

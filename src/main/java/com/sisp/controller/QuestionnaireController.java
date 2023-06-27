package com.sisp.controller;

import com.sisp.beans.HttpResponseEntity;
import com.sisp.dao.entity.AnswerEntity;
import com.sisp.dao.entity.OptionEntity;
import com.sisp.dao.entity.QuestionEntity;
import com.sisp.dao.entity.QuestionnaireEntity;
import com.sisp.service.AnswerService;
import com.sisp.service.OptionService;
import com.sisp.service.QuestionService;
import com.sisp.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

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
    private AnswerService answerService;

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
            httpResponseEntity.setMessage("读取失败");
        } else {
            QuestionnaireEntity questionnaireEntity = questionnaireList.get(0);

            List<QuestionEntity> questionInfos = questionService.queryQuestion(question);
            List<Map<String, Object>> questionList = getQuestionList(questionInfos);

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

        if ( questionnaire.getStatus() == 1 ) {
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

    /**
     * 处理问卷提交
     */
    @PostMapping(value = "/saveCommitAnswer", headers = "Accept=application/json")
    public HttpResponseEntity saveCommitAnswer(@RequestBody List<Map<String, Object>> answerList) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        List<AnswerEntity> answerEntityList = new ArrayList<>();

        for ( Map<String, Object> map : answerList ) {
            int type = Integer.parseInt(map.get("type").toString());

            AnswerEntity answer = new AnswerEntity();
            answer.setQuestionnaireId((String) map.get("questionnaireId"));
            answer.setType(type);
            answer.setQuestionId((String) map.get("questionId"));
            answer.setRespondent((String) map.get("respondent"));

            switch ( type ) {
                case 1:
                case 5:
                    answer.setOptionId((String) map.get("optionId"));
                    answerEntityList.add(answer);
                    break;
                case 2:
                    List<String> optionList = (ArrayList<String>) map.get("optionId");
                    for ( String id : optionList ) {
                        AnswerEntity newAnswer = new AnswerEntity();
                        newAnswer.setQuestionnaireId(answer.getQuestionnaireId());
                        newAnswer.setType(type);
                        newAnswer.setQuestionId(answer.getQuestionId());
                        newAnswer.setRespondent(answer.getRespondent());
                        newAnswer.setOptionId(id);
                        answerEntityList.add(newAnswer);
                    }
                    break;
                case 3:
                    answer.setFillContent((String) map.get("fillContent"));
                    answerEntityList.add(answer);
                    break;
                case 4:
                    List<Map<String, Object>> optionList1 = (ArrayList<Map<String, Object>>) map.get("optionId");
                    for ( Map<String, Object> m : optionList1 ) {
                        AnswerEntity newAnswer = new AnswerEntity();
                        newAnswer.setQuestionnaireId(answer.getQuestionnaireId());
                        newAnswer.setType(type);
                        newAnswer.setQuestionId(answer.getQuestionId());
                        newAnswer.setRespondent(answer.getRespondent());
                        newAnswer.setOptionId((String) m.get("id"));
                        newAnswer.setLeftTitle((String) m.get("leftTitle"));
                        answerEntityList.add(newAnswer);
                    }
                    break;
                default:
                    break;
            }
        }

        int result = answerService.insertBatch(answerEntityList);
        if ( result != 0 ) {
            httpResponseEntity.setCode("666");
            httpResponseEntity.setData(result);
            httpResponseEntity.setMessage("提交成功");
        } else {
            httpResponseEntity.setCode("0");
            httpResponseEntity.setData(0);
            httpResponseEntity.setMessage("提交失败");
        }

        return httpResponseEntity;
    }

    /**
     * 查看问卷回答明细
     */
    @PostMapping(value = "/seeAnswerSheet", headers = "Accept=application/json")
    public HttpResponseEntity seeAnswerSheet(@RequestBody Map<String, Object> answerInfo) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        String questionnaireId = (String) answerInfo.get("id");
        String respondent = (String) answerInfo.get("respondent");

        AnswerEntity answer = new AnswerEntity();
        answer.setQuestionnaireId(questionnaireId);
        answer.setRespondent(respondent);

        QuestionEntity question = new QuestionEntity();
        question.setQuestionnaireId(questionnaireId);

        QuestionnaireEntity questionnaire = new QuestionnaireEntity();
        questionnaire.setId(questionnaireId);

        questionnaire = questionnaireService.queryQuestionnaireList(questionnaire).get(0);
        List<AnswerEntity> answerEntityList = answerService.queryAnswerList(answer);
        List<QuestionEntity> questionEntityList = questionService.queryQuestion(question);

        if ( questionnaire == null ) {
            httpResponseEntity.setCode("0");
            httpResponseEntity.setData(0);
            httpResponseEntity.setMessage("查看明细失败");
        } else {
            List<Map<String, Object>> questionOptionList = getQuestionList(questionEntityList);

            Map<String, Object> params = new HashMap<>();
            params.put("questionnaireId", questionnaireId);
            params.put("questionnaireName", questionnaire.getQuestionnaireName());
            params.put("questionnaireDescription", questionnaire.getQuestionnaireDescription());
            params.put("questionOptionList", questionOptionList);
            params.put("answerList", answerEntityList);

            httpResponseEntity.setCode("666");
            httpResponseEntity.setData(params);
            httpResponseEntity.setMessage("明细查看成功");
        }
        return httpResponseEntity;
    }

    /**
     * 对答题人是否已经作答进行检查
     */
    @PostMapping(value = "/answeredCheck", headers = "Accept=application/json")
    public HttpResponseEntity answeredCheck(@RequestBody Map<String, Object> checkInfo) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        String inputId = (String) checkInfo.get("id");
        String inputName = (String) checkInfo.get("respondent");
        List<Map<String, Object>> list = answerService.answeredCheck();
        for ( Map<String, Object> map : list ) {
            String hasId = (String) map.get("questionnaire_id");
            String hasName = (String) map.get("respondent");
            if ( hasId.equals(inputId) && hasName.equals(inputName) ) {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("该用户已经作答过本问卷，不需再次作答");
                return httpResponseEntity;
            }
        }
        httpResponseEntity.setCode("666");
        httpResponseEntity.setMessage("身份确认成功");
        httpResponseEntity.setData(inputName);
        return httpResponseEntity;
    }

    /**
     * 题目统计
     */
    @PostMapping(value = "/questionnaireStatistic", headers = "Accept=application/json")
    public HttpResponseEntity questionnaireStatistic(@RequestBody QuestionnaireEntity questionnaire) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            List<Map<String, Object>> questionnaireList = questionnaireService.queryQuestionStat(questionnaire);
            if ( CollectionUtils.isEmpty(questionnaireList) ) {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(null);
                httpResponseEntity.setMessage("无问卷的题目统计信息");
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

    /**questionnaireStatistic
     * 获取问题列表的工具方法
     */
    private List<Map<String, Object>> getQuestionList(List<QuestionEntity> list) {
        List<Map<String, Object>> questionList = new ArrayList<>();

        for ( QuestionEntity q : list ) {
            OptionEntity option = new OptionEntity();
            option.setQuestionId(q.getId());
            List<Map<String, Object>> optionList = optionService.queryOption(option);

            Map<String, Object> map = new HashMap<>();
            map.put("questionId", q.getId());
            map.put("problemName", q.getQuestionDescription());
            map.put("mustAnswer", q.isMustAnswer());
            map.put("type", q.getType());
            map.put("leftTitle", q.getLeftTitle());
            map.put("option", optionList);
            questionList.add(map);
        }
        return questionList;
    }
}

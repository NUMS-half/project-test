package com.sisp.controller;

import com.sisp.beans.HttpResponseEntity;
import com.sisp.dao.entity.QuestionnaireEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class QuestionnaireControllerTest {

    @Autowired
    private QuestionnaireController questionnaireController;

    /**
     * 测试查询问卷列表
     */
    @Test
    public void queryQuestionnaireList() {
        QuestionnaireEntity questionnaire = new QuestionnaireEntity();
        questionnaire.setId("999");
        HttpResponseEntity result1 = questionnaireController.queryQuestionnaireList(new QuestionnaireEntity());
        HttpResponseEntity result2 = questionnaireController.queryQuestionnaireList(questionnaire);
        HttpResponseEntity result3 = questionnaireController.queryQuestionnaireList(null);

        //assertions
        assertEquals("666", result1.getCode());
        assertEquals("0", result2.getCode());
        assertEquals("-1", result3.getCode());
    }

    /**
     * 测试新建问卷
     */
    @Test
    @Transactional
    public void addQuestionnaireInfo() {
        QuestionnaireEntity questionnaire = new QuestionnaireEntity();
        questionnaire.setQuestionnaireName("测试添加问卷");
        questionnaire.setQuestionnaireDescription("测试");
        questionnaire.setCreatedBy("admin");
        questionnaire.setCreationDate(new Date());
        questionnaire.setProjectId("333333");
        questionnaire.setType(0);
        questionnaire.setStartTime(new Date());
        questionnaire.setEndTime(new Date());

        HttpResponseEntity result = questionnaireController.addQuestionnaireInfo(questionnaire);

        //assertions
        assertEquals("666", result.getCode());
    }
}
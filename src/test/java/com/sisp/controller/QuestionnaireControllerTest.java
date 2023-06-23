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
        QuestionnaireEntity questionnaire1 = new QuestionnaireEntity();
        QuestionnaireEntity questionnaire2 = new QuestionnaireEntity();

        questionnaire1.setQuestionnaireName("测试添加问卷");
        questionnaire1.setQuestionnaireDescription("测试");
        questionnaire1.setCreatedBy("admin");
        questionnaire1.setCreationDate(new Date());
        questionnaire1.setProjectId("333333");
        questionnaire1.setType(0);
        questionnaire1.setStartTime(new Date());
        questionnaire1.setEndTime(new Date());

        questionnaire2.setQuestionnaireName("aaaaaaaaaasssssssssssssaaassssssaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                                            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                                            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        questionnaire2.setQuestionnaireDescription("测试");
        questionnaire2.setCreatedBy("admin");
        questionnaire2.setCreationDate(new Date());
        questionnaire2.setProjectId("333333");
        questionnaire2.setType(0);
        questionnaire2.setStartTime(new Date());
        questionnaire2.setEndTime(new Date());

        HttpResponseEntity result1 = questionnaireController.addQuestionnaireInfo(questionnaire1);
        HttpResponseEntity result2 = questionnaireController.addQuestionnaireInfo(questionnaire2);

        //assertions
        assertEquals("666", result1.getCode());
        assertEquals("0", result2.getCode());
    }
}
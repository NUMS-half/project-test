package com.sisp.controller;

import com.sisp.beans.HttpResponseEntity;
import com.sisp.dao.entity.QuestionEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class QuestionControllerTest {

    @Autowired
    private QuestionController questionController;

    /**
     * 测试添加问题
     */
    @Test
    @Transactional
    public void addQuestionInfo() {
        QuestionEntity question1 = new QuestionEntity();
        QuestionEntity question2 = new QuestionEntity();

        question1.setId("11111");
        question1.setQuestionIndex(1);
        question1.setQuestionDescription("测试问题描述");
        question1.setQuestionnaireId("5");
        question1.setType(2);
        question1.setMustAnswer(true);

        question2.setId("32222");
        question2.setQuestionnaireId("0");

        HttpResponseEntity result1 = questionController.addQuestionInfo(question1);
        HttpResponseEntity result2 = questionController.addQuestionInfo(question2);

        //assertions
        assertEquals("666", result1.getCode());
        assertEquals("0", result2.getCode());
    }

    /**
     * 测试编辑问题
     */
    @Test
    @Transactional
    public void modifyQuestionInfo() {
        QuestionEntity question1 = new QuestionEntity();
        QuestionEntity question2 = new QuestionEntity();

        question1.setId("9b4a9950c02746e8a117a0f5c76075b6");
        question1.setQuestionDescription("再次修改后的问题描述");
        question2.setId("1111");
        question2.setQuestionnaireId("0");

        HttpResponseEntity result1= questionController.modifyQuestionInfo(question1);
        HttpResponseEntity result2= questionController.modifyQuestionInfo(question2);

        //assertions
        assertEquals("666", result1.getCode());
        assertEquals("0", result2.getCode());

    }
}

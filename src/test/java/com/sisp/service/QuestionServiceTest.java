package com.sisp.service;

import com.sisp.dao.entity.QuestionEntity;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class QuestionServiceTest {

    @Autowired
    private QuestionService questionService;
    Logger log = Logger.getLogger(ProjectServiceTest.class);

    /**
     * 测试添加问题
     */
    @Test
    public void addQuestion() {
        QuestionEntity question= new QuestionEntity();
        question.setId("11111");
        question.setQuestionIndex(1);
        question.setQuestionDescription("测试问题描述");
        question.setQuestionnaireId("5");
        question.setType(2);
        question.setMustAnswer(true);

        int result= questionService.addQuestion(question);

        if (result == 0) {
            log.error(">>test QuestionService: addQuestion添加问题测试失败");
        } else {
            log.info(">>test QuestionService: addQuestion添加问题测试成功");
        }

        //assertion
        assertEquals(1, result);
    }

    /**
     * 测试修改问题
     */
    @Test
    public void modifyQuestion() {
        QuestionEntity question=new QuestionEntity();
        question.setId("9b4a9950c02746e8a117a0f5c76075b6");
        question.setQuestionDescription("修改后的问题描述");

        int result= questionService.modifyQuestion(question);

        if (result == 0) {
            log.error(">>test QuestionService: modifyQuestion修改问题测试失败");
        } else {
            log.info(">>test QuestionService: modifyQuestion修改问题测试成功");
        }

        //assertion
        assertEquals(1, result);

    }

    /**
     * 测试批量插入问题
     */
    @Test
    public void insertBatch() {
        List<QuestionEntity> list = new ArrayList<>();
        QuestionEntity question = new QuestionEntity();
        question.setQuestionnaireId("01333ddfa45845dbbc0ee3b5642f81e9");
        question.setQuestionDescription("问题题目");
        question.setMustAnswer(false);
        question.setType(1);
        question.setQuestionIndex(1);

        QuestionEntity question2 = new QuestionEntity();
        question2.setQuestionnaireId("01333ddfa45845dbbc0ee3b5642f81e9");
        question2.setQuestionDescription("问题题目");
        question2.setMustAnswer(false);
        question2.setType(1);
        question.setQuestionIndex(2);

        list.add(question);
        list.add(question2);

        questionService.insertBatch(list);
    }
}
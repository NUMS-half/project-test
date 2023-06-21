package com.sisp.service;

import com.sisp.dao.entity.QuestionEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class QuestionServiceTest {

    @Autowired
    private QuestionService questionService;

    @Test
    public void addQuestion() {
    }

    @Test
    public void modifyQuestion() {
    }

    @Test
    public void insertBatch() {
        List<QuestionEntity> list = new ArrayList<>();
        QuestionEntity question = new QuestionEntity();
        question.setQuestionnaireId("01333ddfa45845dbbc0ee3b5642f81e9");
        question.setQuestionDescription("问题题目");
        question.setMustAnswer(false);
        question.setType(1);

        QuestionEntity question2 = new QuestionEntity();
        question2.setQuestionnaireId("01333ddfa45845dbbc0ee3b5642f81e9");
        question2.setQuestionDescription("问题题目");
        question2.setMustAnswer(false);
        question2.setType(1);

        list.add(question);
        list.add(question2);

        questionService.insertBatch(list);
    }
}
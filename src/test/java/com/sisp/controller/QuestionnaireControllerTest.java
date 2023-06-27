package com.sisp.controller;

import com.sisp.beans.HttpResponseEntity;
import com.sisp.dao.entity.OptionEntity;
import com.sisp.dao.entity.QuestionEntity;
import com.sisp.dao.entity.QuestionnaireEntity;
import com.sisp.service.OptionService;
import com.sisp.service.QuestionService;
import com.sisp.service.QuestionnaireService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class QuestionnaireControllerTest {

    @InjectMocks
    @Autowired
    private QuestionnaireController questionnaireController;

    @Mock
    @Autowired
    private QuestionnaireService questionnaireService;

    @Mock
    private QuestionService questionService;
    @Mock
    private OptionService optionService;
    private MockMvc mockMvc;
    private MockHttpSession session;
    private MockHttpServletResponse response;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(questionnaireController).build();
    }

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

    /**
     * 测试逻辑删除问卷
     */
    @Test
    @Transactional
    public void deleteQuestionnaire() {
        QuestionnaireEntity questionnaire1 = new QuestionnaireEntity();
        QuestionnaireEntity questionnaire2 = new QuestionnaireEntity();
        questionnaire1.setId("a8954cbf264e448599302d736ca88c86");
        questionnaire2.setId("8888888");

        HttpResponseEntity result1 = questionnaireController.deleteQuestionnaire(questionnaire1);
        HttpResponseEntity result2 = questionnaireController.deleteQuestionnaire(questionnaire2);

        //assertion
        assertEquals("666", result1.getCode());
        assertEquals("0", result2.getCode());
        ;
    }

    /**
     * 测试发布/收回问卷成功
     */
    @Test
    @Transactional
    public void testSetQuestionnaireStatus_publishSuccess() {
        // 创建一个测试用的 QuestionnaireEntity 对象
        QuestionnaireEntity questionnaire = new QuestionnaireEntity();
        questionnaire.setStatus(0);

        // 模拟 questionnaireService.modifyQuestionnaireInfo 方法返回成功
        Mockito.when(questionnaireService.modifyQuestionnaireInfo(questionnaire)).thenReturn(1);

        // 调用 setQuestionnaireStatus 方法
        HttpResponseEntity response = questionnaireController.setQuestionnaireStatus(questionnaire);

        // 验证返回的 HttpResponseEntity 对象是否符合预期
        Assert.assertEquals("666", response.getCode());
        Assert.assertEquals(questionnaire.getId(), response.getData());
        Assert.assertEquals("发布成功", response.getMessage());

        // 验证 questionnaireService.modifyQuestionnaireInfo 方法是否被调用
        Mockito.verify(questionnaireService, Mockito.times(1)).modifyQuestionnaireInfo(questionnaire);
    }

    /**
     * 测试发布/收回问卷失败
     */
    @Test
    @Transactional
    public void testSetQuestionnaireStatus_publishFailure() {
        // 创建一个测试用的 QuestionnaireEntity 对象
        QuestionnaireEntity questionnaire = new QuestionnaireEntity();
        questionnaire.setStatus(0);

        // 模拟 questionnaireService.modifyQuestionnaireInfo 方法返回失败
        Mockito.when(questionnaireService.modifyQuestionnaireInfo(questionnaire)).thenReturn(0);

        // 调用 setQuestionnaireStatus 方法
        HttpResponseEntity response = questionnaireController.setQuestionnaireStatus(questionnaire);

        // 验证返回的 HttpResponseEntity 对象是否符合预期
        Assert.assertEquals("0", response.getCode());
        Assert.assertEquals(0, response.getData());
        Assert.assertEquals("发布失败", response.getMessage());

        // 验证 questionnaireService.modifyQuestionnaireInfo 方法是否被调用
        Mockito.verify(questionnaireService, Mockito.times(1)).modifyQuestionnaireInfo(questionnaire);
    }

}
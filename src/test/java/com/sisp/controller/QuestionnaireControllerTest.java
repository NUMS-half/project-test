package com.sisp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sisp.beans.HttpResponseEntity;
import com.sisp.dao.entity.AnswerEntity;
import com.sisp.dao.entity.OptionEntity;
import com.sisp.dao.entity.QuestionEntity;
import com.sisp.dao.entity.QuestionnaireEntity;
import com.sisp.service.AnswerService;
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
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class QuestionnaireControllerTest {

    @InjectMocks
    @Autowired
    private QuestionnaireController questionnaireController;

    @Mock
    @Autowired
    private QuestionnaireService questionnaireService;
    @Mock
    @Autowired
    private AnswerService answerService;

    @Mock
    private QuestionService questionService;
    @Mock
    private OptionService optionService;
    private MockMvc mockMvc;
    private MockHttpSession session;
    private MockHttpServletResponse response;
    @Autowired
    private WebApplicationContext webApplicationContext;


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
        questionnaire1.setType(1);
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
     * 测试发布/收回问卷成功
     */
    @Test
    @Transactional
    public void testSetQuestionnaireStatus_publishSuccess() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new QuestionnaireController()).build();
        // 创建一个测试用的 QuestionnaireEntity 对象
        QuestionnaireEntity questionnaire = new QuestionnaireEntity();
        questionnaire.setStatus(0);

        // 模拟 questionnaireService.modifyQuestionnaireInfo 方法返回成功
        when(questionnaireService.modifyQuestionnaireInfo(questionnaire)).thenReturn(1);

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
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new QuestionnaireController()).build();
        // 创建一个测试用的 QuestionnaireEntity 对象
        QuestionnaireEntity questionnaire = new QuestionnaireEntity();
        questionnaire.setStatus(0);

        // 模拟 questionnaireService.modifyQuestionnaireInfo 方法返回失败
        when(questionnaireService.modifyQuestionnaireInfo(questionnaire)).thenReturn(0);

        // 调用 setQuestionnaireStatus 方法
        HttpResponseEntity response = questionnaireController.setQuestionnaireStatus(questionnaire);

        // 验证返回的 HttpResponseEntity 对象是否符合预期
        Assert.assertEquals("0", response.getCode());
        Assert.assertEquals(0, response.getData());
        Assert.assertEquals("发布失败", response.getMessage());

        // 验证 questionnaireService.modifyQuestionnaireInfo 方法是否被调用
        Mockito.verify(questionnaireService, Mockito.times(1)).modifyQuestionnaireInfo(questionnaire);
    }

    /**
     *测试查看问题回答明细
     */
    @Test
    public void testSeeAnswerSheet() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new QuestionnaireController()).build();
        // 模拟 questionnaireService.queryQuestionnaireList 方法的返回结果
        when(questionnaireService.queryQuestionnaireList(any(QuestionnaireEntity.class))).thenReturn(Collections.singletonList(new QuestionnaireEntity()));

        Map<String, Object> answerInfo = new HashMap<>();
        answerInfo.put("id", "questionnaireId");
        answerInfo.put("respondent", "respondent");

        HttpResponseEntity expectedResponse = new HttpResponseEntity();
        expectedResponse.setCode("666");
        expectedResponse.setMessage("明细查看成功");

        // 调用被测试的方法
        HttpResponseEntity actualResponse = questionnaireController.seeAnswerSheet(answerInfo);

        // 验证结果
        assertEquals(expectedResponse.getCode(), actualResponse.getCode());
        assertEquals(expectedResponse.getMessage(), actualResponse.getMessage());
    }

    /**
     * 测试题目统计
     */
    @Test
    public void testQuestionnaireStatistic() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new QuestionnaireController()).build();
        // 创建 QuestionnaireEntity 对象
        QuestionnaireEntity questionnaire = new QuestionnaireEntity();
        questionnaire.setId("1");

        // 模拟 questionnaireService.queryQuestionStat 方法的返回结果
        List<Map<String, Object>> expectedQuestionnaireList = new ArrayList<>();
        Map<String, Object> questionStat = new HashMap<>();
        questionStat.put("questionnaire_name", "Questionnaire 1");
        questionStat.put("total_responses", 100);
        expectedQuestionnaireList.add(questionStat);

        // 使用 Mockito 框架模拟 questionnaireService.queryQuestionStat 方法的行为
        when(questionnaireService.queryQuestionStat(questionnaire)).thenReturn(expectedQuestionnaireList);

        // 调用被测试的方法
        HttpResponseEntity actualResponse = questionnaireController.questionnaireStatistic(questionnaire);

        // 验证返回的 HttpResponseEntity 对象的属性值是否与预期一致
        assertEquals("666", actualResponse.getCode());
        assertEquals(expectedQuestionnaireList, actualResponse.getData());
        assertEquals("查询成功", actualResponse.getMessage());
    }

    /**
     * 测试解析访问问卷请求
     */
    @Test
    public void testHandleURL() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new QuestionnaireController()).build();
        // 模拟 GET 请求，传递问卷ID为 "34ee0b5bda2c4cc98dd48707b7160ef2"
        MvcResult result = mockMvc.perform(get("/questionnaireId/{id}", "34ee0b5bda2c4cc98dd48707b7160ef2"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

        // 获取重定向的目标URL
        String redirectedUrl = result.getResponse().getRedirectedUrl();

        // 验证重定向URL是否符合预期
        assertEquals("/pages/error/accessDenied.html", redirectedUrl);
    }



}
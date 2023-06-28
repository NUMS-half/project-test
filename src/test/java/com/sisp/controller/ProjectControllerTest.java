package com.sisp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sisp.beans.HttpResponseEntity;
import com.sisp.dao.entity.ProjectEntity;
import com.sisp.service.ProjectService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {
    @Autowired
    private ProjectController projectController;
    @Mock
    private  ProjectService projectService;
    @Autowired
    private MockMvc mockMvc;
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ProjectController()).build();
    }


    @Test
    public void queryProjectList() {
        ProjectEntity project = new ProjectEntity();
        project.setProjectName("我打赌你找不到这个项目");

        HttpResponseEntity result1 = projectController.queryProjectList(new ProjectEntity());
        HttpResponseEntity result2 = projectController.queryProjectList(project);
        HttpResponseEntity result3 = projectController.queryProjectList(null);

        //assertions
        assertEquals("666", result1.getCode());
        assertEquals("0", result2.getCode());
        assertEquals("-1", result3.getCode());
    }

    @Test
    @Transactional
    public void addProjectInfo() {
        ProjectEntity project = new ProjectEntity();
        ProjectEntity project2 = new ProjectEntity();
        project.setProjectName("t2" + new Random().nextInt(10000) + 1);
        project2.setProjectName("aaaaaaaaaasssssssssssssaaassssssaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        HttpResponseEntity result = projectController.addProjectInfo(project);
        HttpResponseEntity result2 = projectController.addProjectInfo(project2);

        //assertions
        assertEquals("666", result.getCode());
        assertEquals("0", result2.getCode());
    }

    @Test
    @Transactional
    public void modifyProjectInfo() {
        ProjectEntity project1 = new ProjectEntity();
        ProjectEntity project2 = new ProjectEntity();
        project1.setId("1775449254544479b93311dcf6077ec8");
        project2.setId("0");

        HttpResponseEntity result1 = projectController.modifyProjectInfo(project1);
        HttpResponseEntity result2 = projectController.modifyProjectInfo(project2);

        //assertions
        assertEquals("666", result1.getCode());
        assertEquals("0", result2.getCode());
    }

    @Test
    @Transactional
    public void deleteProjectById() {
        ProjectEntity project1 = new ProjectEntity();
        project1.setId("111111111");

        ProjectEntity project2 = new ProjectEntity();
        project2.setId("0");

        HttpResponseEntity result1 = projectController.deleteProjectById(project1);
        HttpResponseEntity result2 = projectController.deleteProjectById(project2);
        HttpResponseEntity result3 = projectController.deleteProjectById(null);

        assertEquals("666", result1.getCode());
        assertEquals("0", result2.getCode());
        assertEquals("-1", result3.getCode());
    }



}
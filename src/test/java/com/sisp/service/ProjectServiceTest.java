package com.sisp.service;

import com.sisp.DemoApplicationTests;
import com.sisp.controller.ProjectController;
import com.sisp.dao.entity.ProjectEntity;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectServiceTest {
    @Autowired
    private  ProjectService projectService;
    Logger log = Logger.getLogger(DemoApplicationTests.class);

    @Test
    public void queryProjectList() {
        ProjectEntity project1 = new ProjectEntity();
        ProjectEntity project2 = new ProjectEntity();
        project1.setProjectName("");
        project2.setProjectName("第一个项目");

        List<ProjectEntity> list1 = projectService.queryProjectList(project1);
        List<ProjectEntity> list2 = projectService.queryProjectList(project2);

        boolean result1 = CollectionUtils.isEmpty(list1);
        boolean result2 = CollectionUtils.isEmpty(list2);

        if ( result1 || result2 ) {
            log.error(">>test ProjectService: queryProjectList项目查询列表测试失败");
        } else {
            System.out.println("test case 1:");
            System.out.println(list1);
            System.out.println("test case 2:");
            System.out.println(list2);
            log.info(">>test ProjectService: queryProjectList项目查询列表测试成功");
        }

        //assertions
        assertFalse(result1);
        assertFalse(result2);
    }

    @Test
    public void addProjectInfo() {
        ProjectEntity project = new ProjectEntity();
        project.setProjectName("t" + new Random().nextInt(10000) + 1);
        project.setProjectContent("project_service_ add_test");
        project.setUserId("8ceeee2995f3459ba1955f85245dc7a5");
        project.setCreatedBy("admin");
        project.setLastUpdatedBy("admin");

        int result = projectService.addProjectInfo(project);

        if ( result == 0 ) {
            log.error(">>test ProjectService: addProjectInfo项目添加测试失败");
        } else {
            log.info(">>test ProjectService: addProjectInfo项目添加测试成功");
        }

        //assertion
        assertEquals(1, result);
    }

    @Test
    @Transactional
    public void testProjectServiceModifyProjectInfo() {
        ProjectEntity project = new ProjectEntity();
        ProjectEntity project2 = new ProjectEntity();

        project.setId("1775449254544479b93311dcf6077ec8");
        project.setProjectName("修改后的第三个测试项目名称");
        project2.setId("1775449254544479b93311dcf6077ec8");
        project2.setProjectName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        int result = projectService.modifyProjectInfo(project);
        int result2 = projectService.modifyProjectInfo(project2);

        if ( result == 0 || result2 != 0 ) {
            log.error(">>test ProjectService: modifyProjectInfo项目修改测试失败");
        } else {
            log.info(">>test ProjectService: modifyProjectInfo项目修改测试成功");
        }

        //assertions
        assertEquals(1, result);
        assertEquals(0, result2);
    }

    @Test
    public void modifyProjectInfo() {
    }

    @Test
    public void deleteProjectById() {
    }

    @Test
    public void queryProjectQuestionnaire() {
    }

    @Test
    public void queryQuestionnaireAnswers() {
    }

    @Test
    public void querySameQuestionStat() {
    }
}
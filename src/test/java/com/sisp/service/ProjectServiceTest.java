package com.sisp.service;

import com.sisp.dao.entity.ProjectEntity;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;

    Logger log = Logger.getLogger(ProjectServiceTest.class);

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
    }

    @Test
    public void modifyProjectInfo() {
    }

    @Test
    public void deleteProjectById() {
    }
}
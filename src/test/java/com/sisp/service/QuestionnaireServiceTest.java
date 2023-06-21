package com.sisp.service;

import com.sisp.dao.entity.QuestionnaireEntity;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class QuestionnaireServiceTest {

    @Resource
    QuestionnaireService questionnaireService = new QuestionnaireService();

    Logger log = Logger.getLogger(QuestionnaireServiceTest.class);

    /**
     * 测试查询问卷列表
     */
    @Test
    public void queryQuestionnaireList() {
        QuestionnaireEntity questionnaire1 = new QuestionnaireEntity();
        QuestionnaireEntity questionnaire2 = new QuestionnaireEntity();
        QuestionnaireEntity questionnaire3 = new QuestionnaireEntity();
        questionnaire1.setProjectId("111111");
        questionnaire2.setQuestionnaireName("爱好调查问卷");
        questionnaire3.setId("1");

        List<QuestionnaireEntity> list1 = questionnaireService.queryQuestionnaireList(questionnaire1);
        List<QuestionnaireEntity> list2 = questionnaireService.queryQuestionnaireList(questionnaire2);
        List<QuestionnaireEntity> list3 = questionnaireService.queryQuestionnaireList(questionnaire3);

        boolean result1 = CollectionUtils.isEmpty(list1);
        boolean result2 = CollectionUtils.isEmpty(list2);
        boolean result3 = CollectionUtils.isEmpty(list3);

        if (result1 || result2 || result3) {
            log.error(">>test QuestionnaireService: queryQuestionnaireList问卷查询列表测试失败");
        } else {
            System.out.println("根据项目id查询");
            System.out.println(list1);
            System.out.println("根据问卷名称查询");
            System.out.println(list2);
            System.out.println("根据问卷id查询");
            System.out.println(list3);

            log.info(">>test QuestionnaireService: queryQuestionnaireList问卷查询列表测试成功");
        }

        //assertions
        assertFalse(result1);
        assertFalse(result2);
    }

    /**
     * 测试添加问卷
     */
    @Test
    @Transactional
    public void addQuestionnaireInfo() {
        QuestionnaireEntity questionnaire = new QuestionnaireEntity();
        questionnaire.setQuestionnaireName("追星情况调查问卷");
        questionnaire.setQuestionnaireDescription("追星情况");
        questionnaire.setCreatedBy("admin");
        questionnaire.setCreationDate(new Date());
        questionnaire.setProjectId("333333");
        questionnaire.setType(0);
        questionnaire.setStartTime(new Date());
        questionnaire.setEndTime(new Date());

        int result = questionnaireService.addQuestionnaireInfo(questionnaire);

        if (result == 0) {
            log.error(">>test QuestionnaireService: addQuestionnaireInfo添加问卷测试失败");
        } else {
            log.info(">>test QuestionnaireService: addQuestionnaireInfo添加问卷测试成功");
        }

        //assertion
        assertEquals(1, result);
    }

    /**
     * 修改问卷信息
     */
    @Test
    @Transactional
    public void modifyQuestionnaireInfo() {
        QuestionnaireEntity questionnaire = new QuestionnaireEntity();
        questionnaire.setId("b8f86c4eb82d4eb09963edc9004ed2df");
        questionnaire.setStatus(1);

        int result = questionnaireService.modifyQuestionnaireInfo(questionnaire);

        if (result == 0) {
            log.error(">>test QuestionnaireService: modifyQuestionnaireInfo项目修改测试失败");
        } else {
            log.info(">>test QuestionnaireService: modifyQuestionnaireInfo项目修改测试成功");
        }

        //assertion
        assertEquals(1, result);
    }

}
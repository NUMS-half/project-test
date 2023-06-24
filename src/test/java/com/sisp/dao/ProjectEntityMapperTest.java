package com.sisp.dao;

import com.sisp.common.utils.MyBatisUtil;
import com.sisp.dao.entity.ProjectEntity;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@SpringBootTest
public class ProjectEntityMapperTest {

    SqlSessionFactory sessionFactory = MyBatisUtil.getSessionFactoryInstance("mybatis-config.xml");

    @Test
    public void queryProjectQuestionnaire() {
        SqlSession sqlSession = sessionFactory.openSession();
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);

        ProjectEntity project = new ProjectEntity();
        project.setId("4cd6ccb65c894eafaa70b12330f8c2f8");
        List<Map<String, Object>> list = projectEntityMapper.queryProjectQuestionnaire(project);

        System.out.println(list);
    }
}
package com.sisp.dao;

import com.sisp.common.utils.MyBatisUtil;
import com.sisp.dao.entity.OptionEntity;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
@SpringBootTest
public class OptionEntityMapperTest {

    SqlSessionFactory sessionFactory = MyBatisUtil.getSessionFactoryInstance("mybatis-config.xml");

    @Test
    public void queryOptionList() {
        SqlSession sqlSession = sessionFactory.openSession();
        OptionEntityMapper optionEntityMapper = sqlSession.getMapper(OptionEntityMapper.class);

        OptionEntity option = new OptionEntity();
        option.setQuestionId("9dc2a0cd2a434f0692aa0005fdfd43da");
        List<Map<String, Object>> list = optionEntityMapper.queryOptionList(option);

        System.out.println(list);
    }
}
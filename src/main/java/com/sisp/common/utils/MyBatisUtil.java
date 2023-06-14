package com.sisp.common.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {

    private MyBatisUtil() {}

    public static SqlSessionFactory getSessionFactoryInstance(String configFilePath) {
        SqlSessionFactory sessionFactory;
        try {
            InputStream inputStream = Resources.getResourceAsStream(configFilePath);
            sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch ( IOException e ) {
            return null;
        }
        return sessionFactory;
    }
}

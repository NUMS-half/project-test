package com.sisp.service;

import com.sisp.dao.AnswerEntityMapper;
import com.sisp.dao.entity.AnswerEntity;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AnswerService {

    @Resource
    private AnswerEntityMapper answerEntityMapper;

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 添加回答
     */
    public int addAnswer(AnswerEntity answer) {
        int result;
        try {
            result = answerEntityMapper.insert(answer);
        } catch ( Exception e ) {
            return 0;
        }
        return result;
    }

    /**
     * 查询回答
     */
    public List<AnswerEntity> queryAnswerList(AnswerEntity answer) {
        return answerEntityMapper.queryAnswer(answer);
    }

    /**
     * 批量插入回答
     */
    public int insertBatch(List<AnswerEntity> list) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        try{
            AnswerEntityMapper answerMapper = sqlSession.getMapper(AnswerEntityMapper.class);
            list.forEach(answerMapper::insert);
            sqlSession.commit();
            sqlSession.rollback();
        } catch ( Exception e ) {
            sqlSession.rollback();
            sqlSession.close();
            return 0;
        }
        sqlSession.close();
        return 1;
    }

    /**
     * 作答检查
     */
    public List<Map<String,Object>> answeredCheck() {
        return answerEntityMapper.answeredCheck();
    }
}

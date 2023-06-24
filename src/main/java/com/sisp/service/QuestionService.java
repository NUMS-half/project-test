package com.sisp.service;

import com.sisp.common.utils.UUIDUtil;
import com.sisp.dao.QuestionEntityMapper;
import com.sisp.dao.entity.QuestionEntity;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class QuestionService {

    @Resource
    private QuestionEntityMapper questionEntityMapper;

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 查询问题
     */
    public List<QuestionEntity> queryQuestion(QuestionEntity question) {
        return questionEntityMapper.queryQuestion(question);
    }

    /**
     * 添加问题
     */
    public int addQuestion(QuestionEntity question) {
        int result;
        question.setId(UUIDUtil.getOneUUID());
        try {
            result = questionEntityMapper.insert(question);
        } catch ( Exception e ) {
            return 0;
        }
        return result;
    }

    /**
     * 修改问题
     */
    public int modifyQuestion(QuestionEntity question) {
        int result;
        try {
            result = questionEntityMapper.updateByPrimaryKeySelective(question);
        } catch ( Exception e ) {
            return 0;
        }
        return result;
    }

    /**
     * 批量插入问题
     */
    public int insertBatch(List<QuestionEntity> list) {
        int i = 0;
        String[] uuids = UUIDUtil.getUUID(list.size());
        for ( QuestionEntity question : list ) {
            question.setId(uuids[i]);
            i++;
        }

        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        try {
            QuestionEntityMapper questionMapper = sqlSession.getMapper(QuestionEntityMapper.class);
            list.forEach(questionMapper::insert);
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
}

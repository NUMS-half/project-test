package com.sisp.service;

import com.sisp.common.utils.UUIDUtil;
import com.sisp.dao.OptionEntityMapper;
import com.sisp.dao.entity.OptionEntity;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class OptionService {

    @Resource
    private OptionEntityMapper optionEntityMapper;

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 添加选项
     */
    public int addOption(OptionEntity option) {
        int result;
        option.setId(UUIDUtil.getOneUUID());
        try {
            result = optionEntityMapper.insert(option);
        } catch ( Exception e ) {
            return 0;
        }
        return result;
    }

    /**
     * 批量插入选项
     */
    public int insertBatch(List<OptionEntity> list) {
        int i = 0;
        String[] uuids = UUIDUtil.getUUID(list.size());
        for ( OptionEntity option : list ) {
            option.setId(uuids[i]);
            i++;
        }

        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        try {
            OptionEntityMapper optionMapper = sqlSession.getMapper(OptionEntityMapper.class);
            list.forEach(optionMapper::insert);
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

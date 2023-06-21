package com.sisp.dao;

import com.sisp.dao.entity.QuestionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface QuestionEntityMapper {

    /**
     * 新建问题
     */
    int insert(QuestionEntity question);

    /**
     * 查询问题
     */
    List<QuestionEntity> queryQuestion(@Param("question") QuestionEntity question);

    /**
     * 修改问题
     */
    int updateByPrimaryKeySelective(QuestionEntity question);

    /**
     * 删除问题
     */
    int deleteQuestion(QuestionEntity question);

}

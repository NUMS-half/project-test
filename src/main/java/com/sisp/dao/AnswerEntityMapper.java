package com.sisp.dao;

import com.sisp.dao.entity.AnswerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AnswerEntityMapper {

    /**
     * 插入回答
     */
    int insert(AnswerEntity answer);

    /**
     * 查询回答
     */
    List<AnswerEntity> queryAnswer(@Param("answer") AnswerEntity answer);
}

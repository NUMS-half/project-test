package com.sisp.dao;

import com.sisp.dao.entity.AnswerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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

    /**
     * 作答检查
     */
    @Select("SELECT questionnaire_id, respondent FROM answer_info GROUP BY questionnaire_id, respondent")
    List<Map<String,Object>> answeredCheck();
}

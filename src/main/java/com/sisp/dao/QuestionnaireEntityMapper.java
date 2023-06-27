package com.sisp.dao;

import com.sisp.dao.entity.QuestionEntity;
import com.sisp.dao.entity.QuestionnaireEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface QuestionnaireEntityMapper {

    /**
     * 搜索调查问卷
     */
    List<QuestionnaireEntity> queryQuestionnaireList(@Param("questionnaire") QuestionnaireEntity questionnaire);

    /**
     * 添加调查问卷
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(QuestionnaireEntity questionnaire);

    /**
     * 修改问卷信息
     */
    int updateByPrimaryKeySelective(QuestionnaireEntity questionnaire);

    /**
     * 问卷题目统计
     */
    @MapKey("questionnaire_name")
    List<Map<String, Object>> queryQuestionStat(@Param("questionnaire") QuestionnaireEntity questionnaire);

}

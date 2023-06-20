package com.sisp.dao;

import com.sisp.dao.entity.QuestionnaireEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

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
    int insert(QuestionnaireEntity questionnaire);

    /**
     * 修改问卷信息
     */
    int updateByPrimaryKeySelective(QuestionnaireEntity questionnaire);

}

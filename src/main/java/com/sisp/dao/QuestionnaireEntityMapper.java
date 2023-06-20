package com.sisp.dao;

import com.sisp.dao.entity.QuestionnaireEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface QuestionnaireEntityMapper {

    List<QuestionnaireEntity> queryQuestionnaireList(@Param("questionnaire") QuestionnaireEntity questionnaire);

}

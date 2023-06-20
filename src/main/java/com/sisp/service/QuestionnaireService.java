package com.sisp.service;

import com.sisp.dao.QuestionnaireEntityMapper;
import com.sisp.dao.entity.QuestionnaireEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionnaireService {

    @Autowired
    private QuestionnaireEntityMapper questionnaireEntityMapper;

    public List<QuestionnaireEntity> queryQuestionnaireList(QuestionnaireEntity questionnaire) {
        return questionnaireEntityMapper.queryQuestionnaireList(questionnaire);
    }
}

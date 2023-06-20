package com.sisp.service;

import com.sisp.common.utils.UUIDUtil;
import com.sisp.dao.QuestionnaireEntityMapper;
import com.sisp.dao.entity.QuestionnaireEntity;
import com.sisp.dao.entity.QuestionnaireType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionnaireService {

    @Autowired
    private QuestionnaireEntityMapper questionnaireEntityMapper;

    /**
     * 查询问卷列表
     */
    public List<QuestionnaireEntity> queryQuestionnaireList(QuestionnaireEntity questionnaire) {
        return questionnaireEntityMapper.queryQuestionnaireList(questionnaire);
    }

    /**
     * 添加问卷
     */
    public int addQuestionnaireInfo(QuestionnaireEntity questionnaire) {
        int result;
        questionnaire.setId(UUIDUtil.getOneUUID());
        try {
            result = questionnaireEntityMapper.insert(questionnaire);
        } catch ( Exception e ) {
            return 0;
        }
        return result;
    }

    /**
     * 修改问卷信息
     */
    public int modifyQuestionnaireInfo(QuestionnaireEntity questionnaire) {
        int result;
        try {
            result = questionnaireEntityMapper.updateByPrimaryKeySelective(questionnaire);
        } catch ( Exception e ) {
            return 0;
        }
        return result;
    }
}

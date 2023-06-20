package com.sisp.dao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QuestionEntity {

    private String id;

    private String questionnaireId;

    private String questionDescription;

    private QuestionType type;

    private String leftTitle;
}

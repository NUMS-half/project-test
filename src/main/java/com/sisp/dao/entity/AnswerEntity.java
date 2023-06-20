package com.sisp.dao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AnswerEntity {

    private QuestionType type;

    private String questionId;

    private String optionId;

    private String fillContent;
}

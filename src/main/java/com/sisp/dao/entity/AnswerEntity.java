package com.sisp.dao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class AnswerEntity implements Serializable {

    private int type;

    private String questionId;

    private String optionId;

    private String fillContent;

    private String respondent;

    private Date answerTime;
}

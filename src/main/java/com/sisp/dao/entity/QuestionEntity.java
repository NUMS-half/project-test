package com.sisp.dao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class QuestionEntity implements Serializable {

    private String id;

    private String questionnaireId;

    private String questionDescription;

    private int type;

    private String leftTitle;

    private boolean mustAnswer;
}

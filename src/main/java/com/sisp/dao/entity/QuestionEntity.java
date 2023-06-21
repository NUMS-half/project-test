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

    private int type = -1;

    private String leftTitle;

    private boolean mustAnswer;

    private int questionIndex = -1;
}

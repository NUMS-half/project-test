package com.sisp.dao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class QuestionnaireEntity {

    private String id;

    private String questionnaireName;

    private String questionnaireDescription;

    private String createdBy;

    private Date creationDate;

    private String projectId;

    private QuestionnaireType type;

    private Date startTime;

    private Date endTime;

    private int status;

}

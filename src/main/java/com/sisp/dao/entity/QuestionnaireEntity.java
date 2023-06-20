package com.sisp.dao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class QuestionnaireEntity implements Serializable {

    private String id;

    private String questionnaireName;

    private String questionnaireDescription;

    private String createdBy;

    private Date creationDate;

    private String projectId;

    private int type;

    private Date startTime;

    private Date endTime;

    private Date publishTime;

    private int status;

}

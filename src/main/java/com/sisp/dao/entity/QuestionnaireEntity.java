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

    private int status = -2; //-2：默认值， -1：逻辑删除状态， 0：发布状态， 1：创建未发布状态

}

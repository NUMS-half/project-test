package com.sisp.dao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class ProjectEntity implements Serializable {

    private String id;

    private String userId;

    private String projectName;

    private String projectContent;

    private String createdBy;

    private Date creationDate;

    private String lastUpdatedBy;

    private Date lastUpdatedDate;
}

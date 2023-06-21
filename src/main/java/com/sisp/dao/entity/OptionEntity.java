package com.sisp.dao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class OptionEntity implements Serializable {

    private String id;

    private String questionId;

    private String chooseTerm;

    private String fraction;
}

package com.sisp.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HttpResponseEntity {

    private String code;     //状态码

    private Object data;     //返回数据

    private String message;  //状态消息
}

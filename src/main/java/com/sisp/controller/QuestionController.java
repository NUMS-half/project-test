package com.sisp.controller;

import com.sisp.beans.HttpResponseEntity;
import com.sisp.dao.entity.QuestionEntity;
import com.sisp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * 添加问题
     */
    @PostMapping(value = "/addQuestionInfo", headers = "Accept=application/json")
    public HttpResponseEntity addQuestionInfo(@RequestBody QuestionEntity question) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        int result = questionService.addQuestion(question);
        if ( result != 0 ) {
            httpResponseEntity.setCode("666");
            httpResponseEntity.setData(result);
            httpResponseEntity.setMessage("创建成功");
        } else {
            httpResponseEntity.setCode("0");
            httpResponseEntity.setData(0);
            httpResponseEntity.setMessage("创建失败,请检查输入是否合法！");
        }
        return httpResponseEntity;
    }

    /**
     * 编辑问题
     */
    public HttpResponseEntity modifyQuestionInfo(@RequestBody QuestionEntity question) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        int result = questionService.modifyQuestion(question);
        if ( result != 0 ) {
            httpResponseEntity.setCode("666");
            httpResponseEntity.setData(result);
            httpResponseEntity.setMessage("编辑成功");
        } else {
            httpResponseEntity.setCode("0");
            httpResponseEntity.setData(0);
            httpResponseEntity.setMessage("编辑失败,请检查您的输入是否合法！");
        }
        return httpResponseEntity;
    }
}

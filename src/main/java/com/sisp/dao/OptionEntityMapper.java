package com.sisp.dao;

import com.sisp.dao.entity.OptionEntity;
import com.sisp.dao.entity.QuestionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface OptionEntityMapper {

    /**
     * 新建选项
     */
    int insert(OptionEntity option);
}

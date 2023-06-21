package com.sisp.dao;

import com.sisp.dao.entity.OptionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface OptionEntityMapper {

    /**
     * 新建选项
     */
    int insert(OptionEntity option);

    /**
     * 修改选项
     */
    int updateByPrimaryKeySelective(OptionEntity option);

    /**
     * 搜索选项
     */
    List<OptionEntity> queryOptionList(@Param("option") OptionEntity option);
}

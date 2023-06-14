package com.sisp.dao;

import com.sisp.dao.entity.ProjectEntity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ProjectEntityMapper {

    /**
     * 搜索项目
     */
    List<ProjectEntity> queryProjectList(@Param("project") ProjectEntity project);

    /**
     * 增加数据
     */
    int insert(ProjectEntity project);

    /**
     * 修改数据
     */
    int updateByPrimaryKeySelective(ProjectEntity project);

    /**
     * 删除项目
     */
    int deleteProjectById(ProjectEntity project);
}

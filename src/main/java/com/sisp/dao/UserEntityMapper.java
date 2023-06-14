package com.sisp.dao;

import com.sisp.dao.entity.UserEntity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserEntityMapper {

    /**
     * 搜索用户
     * @return 目标用户列表
     */
    List<UserEntity> queryUserList();

    /**
     * 验证用户登录
     * @param user 输入的用户信息
     * @return 查找是否成功
     */
    List<UserEntity> selectUserInfo(UserEntity user);

    /**
     * 增加用户
     * @param user 用户信息
     * @return 增加是否成功
     */
    int insert(UserEntity user) throws Exception;


    /**
     * 删除用户
     * @param id 删除的用户id
     * @return 删除是否成功
     */
    int deleteUserById(@Param("id") String id);

    /**
     * 修改用户数据
     * @param user 要修改的用户数据
     * @return 修改是否成功
     */
    int updateByPrimaryKeySelective(UserEntity user);
}

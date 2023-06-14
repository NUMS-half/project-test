package com.sisp.service;

import com.sisp.common.utils.UUIDUtil;
import com.sisp.dao.UserEntityMapper;
import com.sisp.dao.entity.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserEntityMapper userEntityMapper;

    /**
     * 增加用户
     */
    public int addUserInfo(UserEntity user) {
        int result;
        user.setId(UUIDUtil.getOneUUID());
        try{
            result = userEntityMapper.insert(user);
        } catch ( Exception e ) {
            result = 0;
        }

        return result;
    }

    /**
     * 修改用户
     */
    public int modifyUserInfo(UserEntity user) {
        return userEntityMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 删除用户
     */
    public int deleteUserById(UserEntity user) {
        return userEntityMapper.deleteUserById(user.getId());
    }

    /**
     * 查询用户列表
     */
    public List<UserEntity> queryUserList() {
        return userEntityMapper.queryUserList();
    }

    /**
     * 验证用户登录
     */
    public List<UserEntity> selectUserInfo(UserEntity user) {
        return userEntityMapper.selectUserInfo(user);
    }

}

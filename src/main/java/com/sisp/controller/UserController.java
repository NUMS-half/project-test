package com.sisp.controller;

import com.sisp.beans.HttpResponseEntity;
import com.sisp.dao.entity.UserEntity;
import com.sisp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录验证
     */
    @PostMapping(value = "/userLogin", headers = "Accept=application/json")
    public HttpResponseEntity userLogin(@RequestBody UserEntity user) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            List<UserEntity> hasUser = userService.selectUserInfo(user);
            if ( CollectionUtils.isEmpty(hasUser) ) {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(null);
                httpResponseEntity.setMessage("用户名或密码错误");
            } else {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasUser);
                httpResponseEntity.setMessage("登录成功");
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    /**
     * 用户列表查询
     */
    @PostMapping(value = "/queryUserList", headers = "Accept=application/json")
    public HttpResponseEntity queryUserList() {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        List<UserEntity> hasUser = userService.queryUserList();
//        if ( CollectionUtils.isEmpty(hasUser) ) {
//            httpResponseEntity.setCode("0");
//            httpResponseEntity.setData(null);
//            httpResponseEntity.setMessage("暂无用户信息");
//        } else {
            httpResponseEntity.setCode("666");
            httpResponseEntity.setData(hasUser);
            httpResponseEntity.setMessage("查询成功");
//        }
        return httpResponseEntity;
    }

    /**
     * 用户添加
     */
    @PostMapping(value = "/addUserInfo", headers = "Accept=application/json")
    public HttpResponseEntity addUser(@RequestBody UserEntity user) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        int result = userService.addUserInfo(user);
        if ( result != 0 ) {
            httpResponseEntity.setCode("666");
            httpResponseEntity.setData(result);
            httpResponseEntity.setMessage("创建成功");
        } else {
            httpResponseEntity.setCode("0");
            httpResponseEntity.setData(0);
            httpResponseEntity.setMessage("创建失败");
        }
        return httpResponseEntity;
    }

    /**
     * 用户修改
     */
    @PostMapping(value = "/modifyUserInfo", headers = "Accept=application/json")
    public HttpResponseEntity modifyUserInfo(@RequestBody UserEntity user) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            int result = userService.modifyUserInfo(user);
            if ( result != 0 ) {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("修改成功");
            } else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("修改失败");
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    /**
     * 用户删除
     */
    @PostMapping(value = "/deleteUserinfo", headers = "Accept=application/json")
    public HttpResponseEntity deleteUser(@RequestBody UserEntity user) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            int result = userService.deleteUserById(user);
            if ( result != 0 ) {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("删除成功");
            } else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("删除失败");
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return httpResponseEntity;
    }
}

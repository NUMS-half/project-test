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
            if ( user == null ) {
                throw new NullPointerException();
            }
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
            httpResponseEntity.setCode("-1");
            httpResponseEntity.setData(null);
            httpResponseEntity.setMessage("登录时发生异常，请稍后重试");
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
        httpResponseEntity.setCode("666");
        httpResponseEntity.setData(hasUser);
        httpResponseEntity.setMessage("查询成功");
        return httpResponseEntity;
    }

    /**
     * 用户添加
     */
    @PostMapping(value = "/addUserInfo", headers = "Accept=application/json")
    public HttpResponseEntity addUser(@RequestBody UserEntity user) {
        int result = userService.addUserInfo(user);
        return handleResult(result);
    }

    /**
     * 用户修改
     */
    @PostMapping(value = "/modifyUserInfo", headers = "Accept=application/json")
    public HttpResponseEntity modifyUserInfo(@RequestBody UserEntity user) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            int result = userService.modifyUserInfo(user);
            httpResponseEntity = handleResult(result);
        } catch ( Exception e ) {
            httpResponseEntity.setCode("-1");
            httpResponseEntity.setData(0);
            httpResponseEntity.setMessage("修改发生异常，请稍后重试！");
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
            httpResponseEntity = handleResult(result);
        } catch ( Exception e ) {
            httpResponseEntity.setCode("-1");
            httpResponseEntity.setData(0);
            httpResponseEntity.setMessage("删除发生异常，请稍后重试！");
        }
        return httpResponseEntity;
    }

    private HttpResponseEntity handleResult(int result) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        if ( result != 0 ) {
            httpResponseEntity.setCode("666");
            httpResponseEntity.setData(result);
            httpResponseEntity.setMessage("操作成功！");
        } else {
            httpResponseEntity.setCode("0");
            httpResponseEntity.setData(0);
            httpResponseEntity.setMessage("操作失败！");
        }
        return httpResponseEntity;
    }

}

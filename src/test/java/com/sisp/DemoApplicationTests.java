package com.sisp;

import static org.junit.jupiter.api.Assertions.*;

import com.sisp.beans.HttpResponseEntity;
import com.sisp.common.utils.MyBatisUtil;
import com.sisp.common.utils.UUIDUtil;
import com.sisp.controller.ProjectController;
import com.sisp.controller.UserController;
import com.sisp.dao.ProjectEntityMapper;
import com.sisp.dao.UserEntityMapper;
import com.sisp.dao.entity.ProjectEntity;
import com.sisp.dao.entity.UserEntity;
import com.sisp.service.ProjectService;
import com.sisp.service.UserService;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class DemoApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserController userController;

    @Autowired
    private ProjectController projectController;

    Logger log = Logger.getLogger(DemoApplicationTests.class);

    SqlSessionFactory sessionFactory = MyBatisUtil.getSessionFactoryInstance("mybatis-config.xml");

    /**
     * test DemoApplication
     */

    @Test
    void testDemoApplicationMain() {
        String[] args = {"test"};

        DemoApplication application = new DemoApplication();
        DemoApplication.main(args);

        //assertion
        assertNotNull(application);
    }

    /**
     * test HttpResponseEntity
     */

    @Test
    void testHttpResponseEntity() {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        httpResponseEntity.setCode("666");
        httpResponseEntity.setData("test data");
        httpResponseEntity.setMessage("test");

        //assertions
        assertEquals("666", httpResponseEntity.getCode());
        assertEquals("test data", httpResponseEntity.getData());
        assertEquals("test", httpResponseEntity.getMessage());
    }

    /**
     * test UUIDUtil
     */

    @Test
    void testUUIDUtilGetOneUUID() {
        String uuid = UUIDUtil.getOneUUID();
        System.out.println(uuid);

        //assertion
        assertEquals(32, uuid.length());
    }

    @Test
    void testUUIDUtilGetUUID() {
        String[] uuids = UUIDUtil.getUUID(5);
        String[] emptyUuids = UUIDUtil.getUUID(-1);

        //assertions
        assert uuids.length != 0;
        assertEquals(5, uuids.length);
        assertEquals(0, emptyUuids.length);
    }

    /**
     * test MyBatisUtil
     */

    @Test
    void testMyBatisUtil() {
        SqlSessionFactory factory = MyBatisUtil.getSessionFactoryInstance("nothing.bak");

        //assertion
        assertNull(factory);
    }

    /**
     * test UserEntity
     */

    @Test
    void testUserEntityMethods() {
        UserEntity user = new UserEntity();
        user.setId("123123123");
        user.setUsername("test");
        user.setPassword("123456");
        user.setCreatedBy("admin");
        user.setCreationDate(new Date(1));
        user.setStatus("1");
        user.setLastUpdatedBy("admin");
        user.setLastUpdateDate(new Date(2));

        //assertions
        assertEquals("123123123", user.getId());
        assertEquals("test", user.getUsername());
        assertEquals("123456", user.getPassword());
        assertEquals("admin", user.getCreatedBy());
        assertEquals("Thu Jan 01 08:00:00 CST 1970", user.getCreationDate().toString());
        assertEquals("1", user.getStatus());
        assertEquals("admin", user.getLastUpdatedBy());
        assertEquals("Thu Jan 01 08:00:00 CST 1970", user.getLastUpdateDate().toString());

        System.out.println(user);
    }

    /**
     * test ProjectEntity
     */

    @Test
    void testProjectEntityMethods() {
        ProjectEntity project = new ProjectEntity();
        project.setId("111111");
        project.setUserId("23442");
        project.setProjectName("项目");
        project.setProjectContent("222222");
        project.setCreatedBy("admin");
        project.setCreationDate(new Date(1));
        project.setLastUpdatedDate(new Date(2));
        project.setLastUpdatedBy("admin");

        //assertions
        assertEquals("111111", project.getId());
        assertEquals("23442", project.getUserId());
        assertEquals("项目", project.getProjectName());
        assertEquals("222222", project.getProjectContent());
        assertEquals("admin", project.getCreatedBy());
        assertEquals("Thu Jan 01 08:00:00 CST 1970", project.getCreationDate().toString());
        assertEquals("Thu Jan 01 08:00:00 CST 1970", project.getLastUpdatedDate().toString());
        assertEquals("admin", project.getLastUpdatedBy());

        System.out.println(project);
    }

    /**
     * test UserEntityMapper
     */

    @Test
    void testUserMapperQueryUserList() {
        SqlSession sqlSession = sessionFactory.openSession();
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);

        List<UserEntity> list = userEntityMapper.queryUserList();
        boolean result = CollectionUtils.isEmpty(list);

        if ( !result ) {
            System.out.println(list);
            log.info(">>test UserMapper: queryUserList用户列表查询测试成功");
        } else {
            log.error(">>test UserMapper: queryUserList用户列表查询测试失败");
        }

        //assertion
        assertFalse(result);
    }

    @Test
    void testUserMapperSelectUserInfo() {
        SqlSession sqlSession = sessionFactory.openSession();
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("admin");
        userEntity.setPassword("123456");

        List<UserEntity> list = userEntityMapper.selectUserInfo(userEntity);
        boolean result = CollectionUtils.isEmpty(list);

        if ( result ) {
            log.error(">>test UserMapper: selectUserInfo用户登录测试失败");
        } else {
            System.out.println(list);
            log.info(">>test UserMapper: selectUserInfo用户登录测试成功");
        }

        //assertion
        assertFalse(result);
    }

    @Test
    void testUserMapperInsert() throws Exception {
        SqlSession sqlSession = sessionFactory.openSession();
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(UUIDUtil.getOneUUID());
        userEntity.setStatus("1");
        userEntity.setUsername("mt" + new Random().nextInt(10000) + 1);
        userEntity.setPassword("123");

        int result = userEntityMapper.insert(userEntity);

        if ( result == 0 ) {
            log.error(">>test UserMapper: insert用户插入测试失败");
        } else {
            log.info(">>test UserMapper: insert用户插入测试成功");
        }

        //assertion
        assertEquals(1, result);

        sqlSession.close();
    }

    @Test
    void testUserMapperUpdateByPrimaryKeySelective() {
        SqlSession sqlSession = sessionFactory.openSession();
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);

        UserEntity userEntity = new UserEntity();
        userEntity.setId("e051b86740dc4c49978240a558433938");
        userEntity.setUsername("update");

        int result = userEntityMapper.updateByPrimaryKeySelective(userEntity);

        if ( result == 0 ) {
            log.error(">>test UserMapper: updateByPrimaryKeySelective用户编辑测试失败");
        } else {
            System.out.println(result);
            log.info(">>test UserMapper: updateByPrimaryKeySelective用户编辑测试成功");
        }

        //assertions
        assertEquals(1, result);

        sqlSession.close();
    }

    @Test
    void testUserMapperDeleteUserById() {
        SqlSession sqlSession = sessionFactory.openSession();
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);

        UserEntity userEntity = new UserEntity();
        userEntity.setId("a3777b80bb6f4f4583629f67da26c921");

        int result = userEntityMapper.deleteUserById(userEntity.getId());

        if ( result == 0 ) {
            log.error(">>test UserMapper: deleteUserById用户删除测试失败");
        } else {
            log.info(">>test UserMapper: deleteUserById用户删除测试成功");
        }

        //assertion
        assertEquals(1, result);

        sqlSession.close();
    }

    /**
     * test ProjectEntityMapper
     */

    @Test
    void testProjectMapperQueryProjectList() {
        SqlSession sqlSession = sessionFactory.openSession();
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);

        List<ProjectEntity> list = projectEntityMapper.queryProjectList(new ProjectEntity());
        boolean result = CollectionUtils.isEmpty(list);

        if ( !result ) {
            System.out.println(list);
            log.info(">>test ProjectMapper: queryProjectList项目列表查询测试成功");
        } else {
            log.error(">>test ProjectMapper: queryProjectList项目列表查询测试失败");
        }

        //assertion
        assertFalse(result);
    }

    @Test
    void testProjectMapperInsert() {
        SqlSession sqlSession = sessionFactory.openSession();
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);

        ProjectEntity project = new ProjectEntity();
        project.setId(UUIDUtil.getOneUUID());
        project.setProjectName("第一个测试项目");
        project.setProjectContent("第一个测试项目描述");
        project.setUserId("8ceeee2995f3459ba1955f85245dc7a5");

        int result = projectEntityMapper.insert(project);

        if ( result == 0 ) {
            log.error(">>test ProjectMapper: insert项目插入测试失败");
        } else {
            log.info(">>test ProjectMapper: insert项目插入测试成功");
        }

        //assertion
        assertEquals(1, result);

        sqlSession.close();
    }

    @Test
    void testProjectMapperUpdateByPrimaryKeySelective() {
        SqlSession sqlSession = sessionFactory.openSession();
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);

        ProjectEntity project = new ProjectEntity();
        project.setId("283dcf241cf245aea824dc10bbb3d680");
        project.setProjectName("修改后的第一个项目名称");
        project.setProjectContent("修改后的第一个项目内容");

        int result = projectEntityMapper.updateByPrimaryKeySelective(project);

        if ( result == 0 ) {
            log.error(">>test ProjectMapper: updateByPrimaryKeySelective项目编辑测试失败");
        } else {
            log.info(">>test ProjectMapper: updateByPrimaryKeySelective项目编辑测试成功");
        }

        //assertion
        assertEquals(1, result);

        sqlSession.close();
    }

    @Test
    void testProjectMapperDeleteProjectById() {
        SqlSession sqlSession = sessionFactory.openSession();
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);

        ProjectEntity project = new ProjectEntity();
        project.setId("4cd6ccb65c894eafaa70b12330f8c2f8");

        int result = projectEntityMapper.deleteProjectById(project);

        if ( result == 0 ) {
            log.error(">>test ProjectMapper: deleteProjectById项目删除测试失败");
        } else {
            log.info(">>test ProjectMapper: deleteProjectById项目删除测试成功");
        }

        //assertion
        assertEquals(1, result);

        sqlSession.close();
    }

    /**
     * test UserService
     */

    @Test
    @Transactional
    void testUserServiceAddUserInfo() {
        UserEntity user = new UserEntity();
        user.setUsername("t" + new Random().nextInt(10000) + 1);
        user.setPassword("test_pswd");

        int result = userService.addUserInfo(user);

        if ( result == 0 ) {
            log.error(">>test UserService: addUserInfo用户添加测试失败");
        } else {
            log.info(">>test UserService: addUserInfo用户添加测试成功");
        }

        //assertion
        assertEquals(1, result);
    }

    @Test
    @Transactional
    void testUserServiceModifyUserInfo() {
        UserEntity user = new UserEntity();
        user.setId("e051b86740dc4c49978240a558433938");
        user.setPassword("update");

        int result = userService.modifyUserInfo(user);

        if ( result == 0 ) {
            log.error(">>test UserService: modifyUserInfo用户修改测试失败");
        } else {
            log.info(">>test UserService: modifyUserInfo用户修改测试成功");
        }

        //assertion
        assertEquals(1, result);
    }

    @Test
    @Transactional
    void testUserServiceDeleteUserById() {
        UserEntity user = new UserEntity();
        user.setId("123456789");

        int result = userService.deleteUserById(user);

        if ( result == 0 ) {
            log.error(">>test UserService: deleteUserById用户删除测试失败");
        } else {
            log.info(">>test UserService: deleteUserById用户删除测试成功");
        }

        //assertion
        assertEquals(1, result);
    }

    @Test
    void testUserServiceQueryUserList() {
        List<UserEntity> list = userService.queryUserList();
        boolean result = CollectionUtils.isEmpty(list);

        if ( result ) {
            log.error(">>test UserService: queryUserList用户列表查询测试失败");
        } else {
            System.out.println(list);
            log.info(">>test UserService: queryUserList用户列表查询测试成功");
        }

        //assertion
        assertFalse(result);
    }

    @Test
    void UserServiceSelectUserInfo() {
        UserEntity user = new UserEntity();
        user.setUsername("admin");
        user.setPassword("123456");

        List<UserEntity> list = userService.selectUserInfo(user);
        boolean result = CollectionUtils.isEmpty(list);

        if ( result ) {
            log.error(">>test UserService: selectUserInfo用户登录测试失败");
        } else {
            log.info(">>test UserService: selectUserInfo用户登录测试成功");
            System.out.println(list);
        }

        //assertion
        assertFalse(result);
    }

    /**
     * test ProjectService
     */

//    @Test
//    void testProjectServiceQueryProjectList() {
//        ProjectEntity project1 = new ProjectEntity();
//        ProjectEntity project2 = new ProjectEntity();
//        project1.setProjectName("");
//        project2.setProjectName("第一个项目");
//
//        List<ProjectEntity> list1 = projectService.queryProjectList(project1);
//        List<ProjectEntity> list2 = projectService.queryProjectList(project2);
//
//        boolean result1 = CollectionUtils.isEmpty(list1);
//        boolean result2 = CollectionUtils.isEmpty(list2);
//
//        if ( result1 || result2 ) {
//            log.error(">>test ProjectService: queryProjectList项目查询列表测试失败");
//        } else {
//            System.out.println("test case 1:");
//            System.out.println(list1);
//            System.out.println("test case 2:");
//            System.out.println(list2);
//            log.info(">>test ProjectService: queryProjectList项目查询列表测试成功");
//        }
//
//        //assertions
//        assertFalse(result1);
//        assertFalse(result2);
//    }

    @Test
    @Transactional
    void testProjectServiceAddProjectInfo() {
        ProjectEntity project = new ProjectEntity();
        project.setProjectName("t" + new Random().nextInt(10000) + 1);
        project.setProjectContent("project_service_ add_test");
        project.setUserId("8ceeee2995f3459ba1955f85245dc7a5");
        project.setCreatedBy("admin");
        project.setLastUpdatedBy("admin");

        int result = projectService.addProjectInfo(project);

        if ( result == 0 ) {
            log.error(">>test ProjectService: addProjectInfo项目添加测试失败");
        } else {
            log.info(">>test ProjectService: addProjectInfo项目添加测试成功");
        }

        //assertion
        assertEquals(1, result);
    }

    @Test
    @Transactional
    void testProjectServiceModifyProjectInfo() {
        ProjectEntity project = new ProjectEntity();
        ProjectEntity project2 = new ProjectEntity();

        project.setId("1775449254544479b93311dcf6077ec8");
        project.setProjectName("修改后的第三个测试项目名称");
        project2.setId("1775449254544479b93311dcf6077ec8");
        project2.setProjectName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        int result = projectService.modifyProjectInfo(project);
        int result2 = projectService.modifyProjectInfo(project2);

        if ( result == 0 || result2 != 0 ) {
            log.error(">>test ProjectService: modifyProjectInfo项目修改测试失败");
        } else {
            log.info(">>test ProjectService: modifyProjectInfo项目修改测试成功");
        }

        //assertions
        assertEquals(1, result);
        assertEquals(0, result2);
    }

    @Test
    @Transactional
    void testProjectServiceDeleteProjectById() {
        ProjectEntity project = new ProjectEntity();
        project.setId("123456789");

        int result = projectService.deleteProjectById(project);

        if ( result == 0 ) {
            log.error(">>test ProjectService: deleteProjectById项目删除测试失败");
        } else {
            log.info(">>test ProjectService: deleteProjectById项目删除测试成功");
        }

        //assertion
        assertEquals(1, result);
    }

    /**
     * test UserController
     */

    @Test
    void testUserControllerUserLogin() {
        UserEntity user1 = new UserEntity();
        user1.setUsername("admin");
        user1.setPassword("123456");
        HttpResponseEntity result1 = userController.userLogin(user1);

        UserEntity user2 = new UserEntity();
        user2.setUsername("admin");
        user2.setPassword("111111");
        HttpResponseEntity result2 = userController.userLogin(user2);

        HttpResponseEntity result3 = userController.userLogin(null);

        //assertions
        assertEquals("666", result1.getCode());
        assertEquals("0", result2.getCode());
        assertEquals("-1", result3.getCode());
    }

    @Test
    void testUserControllerQueryUserList() {
        HttpResponseEntity result = userController.queryUserList();

        //assertion
        assertEquals("666", result.getCode());
    }

    @Test
    @Transactional
    void testUserControllerAddUser() {
        UserEntity user = new UserEntity();
        user.setId(UUIDUtil.getOneUUID());
        user.setUsername("t2" + new Random().nextInt(10000) + 1);
        user.setPassword("test_pswd");

        HttpResponseEntity result = userController.addUser(user);

        user.setUsername(user.getUsername());
        HttpResponseEntity result2 = userController.addUser(user);

        //assertions
        assertEquals("666", result.getCode());
        assertEquals("0", result2.getCode());
    }

    @Test
    @Transactional
    void testUserControllerModifyUserInfo() {
        UserEntity user1 = new UserEntity();
        user1.setId("e051b86740dc4c49978240a558433938");
        user1.setPassword("update2");

        UserEntity user2 = new UserEntity();
        user2.setId("0");
        user2.setPassword("update3");

        HttpResponseEntity result1 = userController.modifyUserInfo(user1);
        HttpResponseEntity result2 = userController.modifyUserInfo(user2);
        HttpResponseEntity result3 = userController.modifyUserInfo(null);

        assertEquals("666", result1.getCode());
        assertEquals("0", result2.getCode());
        assertEquals("-1", result3.getCode());
    }

    @Test
    @Transactional
    void testUserControllerDeleteUser() {
        UserEntity user1 = new UserEntity();
        user1.setId("111111111");

        UserEntity user2 = new UserEntity();
        user2.setId("0");

        HttpResponseEntity result1 = userController.deleteUser(user1);
        HttpResponseEntity result2 = userController.deleteUser(user2);
        HttpResponseEntity result3 = userController.deleteUser(null);

        assertEquals("666", result1.getCode());
        assertEquals("0", result2.getCode());
        assertEquals("-1", result3.getCode());
    }

    /**
     * test ProjectController
     */

    @Test
    void testProjectControllerQueryProjectList() {
        ProjectEntity project = new ProjectEntity();
        project.setProjectName("我打赌你找不到这个项目");

        HttpResponseEntity result1 = projectController.queryProjectList(new ProjectEntity());
        HttpResponseEntity result2 = projectController.queryProjectList(project);
        HttpResponseEntity result3 = projectController.queryProjectList(null);

        //assertions
        assertEquals("666", result1.getCode());
        assertEquals("0", result2.getCode());
        assertEquals("-1", result3.getCode());
    }

    @Test
    @Transactional
    void testProjectControllerAddProjectInfo() {
        ProjectEntity project = new ProjectEntity();
        ProjectEntity project2 = new ProjectEntity();
        project.setProjectName("t2" + new Random().nextInt(10000) + 1);
        project2.setProjectName("aaaaaaaaaasssssssssssssaaassssssaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        HttpResponseEntity result = projectController.addProjectInfo(project);
        HttpResponseEntity result2 = projectController.addProjectInfo(project2);

        //assertions
        assertEquals("666", result.getCode());
        assertEquals("0", result2.getCode());
    }

    @Test
    @Transactional
    void testProjectControllerModifyProjectInfo() {
        ProjectEntity project1 = new ProjectEntity();
        ProjectEntity project2 = new ProjectEntity();
        project1.setId("1775449254544479b93311dcf6077ec8");
        project2.setId("0");

        HttpResponseEntity result1 = projectController.modifyProjectInfo(project1);
        HttpResponseEntity result2 = projectController.modifyProjectInfo(project2);

        //assertions
        assertEquals("666", result1.getCode());
        assertEquals("0", result2.getCode());
    }

    @Test
    @Transactional
    void testProjectControllerDeleteProjectById() {
        ProjectEntity project1 = new ProjectEntity();
        project1.setId("111111111");

        ProjectEntity project2 = new ProjectEntity();
        project2.setId("0");

        HttpResponseEntity result1 = projectController.deleteProjectById(project1);
        HttpResponseEntity result2 = projectController.deleteProjectById(project2);
        HttpResponseEntity result3 = projectController.deleteProjectById(null);

        assertEquals("666", result1.getCode());
        assertEquals("0", result2.getCode());
        assertEquals("-1", result3.getCode());
    }

}
package com.xjj.ssh.test;

/**
 * Created by Administrator on 2018/6/13 0013.
 */

import com.ssh.controller.UserController;
import com.ssh.service.IUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:spring-mvc.xml"})
public class test{

    // 自动注入
    @Autowired
    private IUserService userService;

    @Autowired
    private UserController userController;

    @Test
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void test(){
        System.out.println("测试Spring整合Junit4进行单元测试");
        //sshCotrollerTest.test();
        /*UserTEntity userTEntity =  userService.loadUser(1);
        System.out.println("password:"+userTEntity.getPassword());
        UserTEntity addUser = new UserTEntity();
        addUser.setPassword("3234");
        addUser.setUsername("xjjd");
        addUser.setId(new BigDecimal(2));*/
        //System.out.println(userService.addUser(addUser));
       /* UserTEntity userTEntity = userService.loadUser("xjj","876543");
        System.out.println("is null:"+userTEntity==null);
        System.out.println("password:"+userTEntity.getPassword());*/

        userController.adduser();

    }

}

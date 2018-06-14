package com.ssh.controller;

import com.ssh.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/6/14 0014.
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;
    @RequestMapping(value = "adduser",method = RequestMethod.GET)
    public String adduser(){
        userService.loadUser(1);
        System.out.println(userService.addUser("xjj14","876543",new BigDecimal(8)));
        return "test";
    }
}

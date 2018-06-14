package com.ssh.controller;

import com.ssh.entity.UserTEntity;
import com.ssh.service.IUserService;
import com.ssh.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/6/9 0009.
 */
@Controller
@RequestMapping("/test")
public class SSHCotrollerTest {
    @Autowired
    private IUserService userService;


    // 登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String test(HttpServletRequest request, Model model) {

        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");

        UserTEntity userTEntity = userService.loadUser(userName, passWord);

        if(userTEntity!=null) {
            model.addAttribute("user",userTEntity);
            return "success";
        }else{
            return "failed";
        }
    }

    // 跳转到登录页面
    @RequestMapping(value = "/loginIndex", method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }

}

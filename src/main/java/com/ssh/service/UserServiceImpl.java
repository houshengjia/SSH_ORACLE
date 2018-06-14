package com.ssh.service;

import com.ssh.dao.IUserDao;
import com.ssh.entity.UserTEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/6/12 0012.
 */
@Service
public class UserServiceImpl implements  IUserService{

    @Autowired
    private IUserDao userDao;

    // 根据id查询用户
    public UserTEntity loadUser(int id){
        return userDao.loadUser(id);
    }

    // 添加用户
    @Transactional
    public BigDecimal addUser(String userName,String passWord,BigDecimal Id) {
        UserTEntity user = new UserTEntity();
        user.setUsername(userName);
        user.setPassword(passWord);
        user.setId(Id);
        return userDao.addUser(user);
    }

    public UserTEntity loadUser(String userName,String passWord){
        return userDao.loadUser(userName,passWord);
    }

}

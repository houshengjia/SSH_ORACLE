package com.ssh.dao;

import com.ssh.entity.UserTEntity;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/6/11 0011.
 */
public interface IUserDao {

    public BigDecimal addUser(UserTEntity user);

    public UserTEntity loadUser(int id);

    public UserTEntity loadUser(String userName,String passWord);

}

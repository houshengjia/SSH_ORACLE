package com.ssh.service;

import com.ssh.entity.UserTEntity;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/6/12 0012.
 */
public interface IUserService {

     BigDecimal addUser(String userName,String passWord,BigDecimal Id);

     UserTEntity loadUser(int id);

     UserTEntity loadUser(String userName,String passWord);
}

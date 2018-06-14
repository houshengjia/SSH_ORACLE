package com.ssh.dao;

import com.ssh.entity.UserTEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resources;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import org.hibernate.Query;

/**
 * Created by Administrator on 2018/6/11 0011.
 */
@Repository("IUserDao")
public class UserDaoImpl extends BaseDao<UserTEntity> implements IUserDao{

    /*@Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }*/
    // 保存用户
    public BigDecimal addUser(UserTEntity user) {
        System.out.println("password+"+user.getPassword());
        return (BigDecimal) getSession().save(user);
    }

    // 根据id查询用户
    public UserTEntity loadUser(int id){
        System.out.println("null:"+ getSession() == null);
        System.out.println("id:"+id);
        BigDecimal b = new BigDecimal(id);
        return (UserTEntity) this.getSession().load(UserTEntity.class, b);
    }

    // 根据用户名，密码查询
    public UserTEntity loadUser(String userName,String passWord){
        String hql = "from UserTEntity where username=? and password=?";
        UserTEntity user = (UserTEntity) queryObject(hql,new Object[]{userName,passWord});
        return user;
    }


}

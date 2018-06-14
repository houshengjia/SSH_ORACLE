package com.ssh.dao;

import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
public class BaseDao<T> implements IBaseDao<T> {

	private SessionFactory sessionFactory;
	/**
	 * ����һ��Class�Ķ�������ȡ���͵�class
	 */
	private Class<?> clz;
	
	public Class<?> getClz() {
		if(clz==null) {
			
			clz = ((Class<?>)
					(((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
		}
		return clz;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/* (non-Javadoc)
	 * @see org.konghao.baisc.dao.IBaseDao#add(java.lang.Object)
	 */
	public T add(T t) {
		getSession().save(t);
		return t;
	}

	/* (non-Javadoc)
	 * @see org.konghao.baisc.dao.IBaseDao#update(java.lang.Object)
	 */
	public void update(T t) {
		getSession().update(t);

	}

	/* (non-Javadoc)
	 * @see org.konghao.baisc.dao.IBaseDao#delete(int)
	 */
	public void delete(int id) {
		getSession().delete(this.load(id));
	}
	
	/* (non-Javadoc)
	 * @see org.konghao.baisc.dao.IBaseDao#load(int)
	 */
	public T load(int id) {
		return (T)getSession().load(getClz(), id);
	}


	/* (non-Javadoc)
	 * @see org.konghao.baisc.dao.IBaseDao#list(java.lang.String, java.lang.Object)
	 */
	public List<T> list(String hql, Object arg) {
		return this.list(hql, new Object[]{arg});
	}

	/* (non-Javadoc)
	 * @see org.konghao.baisc.dao.IBaseDao#list(java.lang.String)
	 */
	public List<T> list(String hql) {
		return this.list(hql,null);
	}
	

	@SuppressWarnings("rawtypes")
	private void setAliasParameter(Query query,Map<String,Object> alias) {
		if(alias!=null) {
			Set<String> keys = alias.keySet();
			for(String key:keys) {
				Object val = alias.get(key);
				if(val instanceof Collection) {
					//��ѯ�������б�
					query.setParameterList(key, (Collection)val);
				} else {
					query.setParameter(key, val);
				}
			}
		}
	}
	
	private void setParameter(Query query,Object[] args) {
		if(args!=null&&args.length>0) {
			int index = 0;
			for(Object arg:args) {
				query.setParameter(index++, arg);
			}
		}
	}





	private String getCountHql(String hql,boolean isHql) {
		String e = hql.substring(hql.indexOf("from"));
		String c = "select count(*) "+e;
		if(isHql)
			c = c.replaceAll("fetch", "");
		return c;
	}

	/* (non-Javadoc)
	 * @see org.konghao.baisc.dao.IBaseDao#queryObject(java.lang.String, java.lang.Object[])
	 */
	public Object queryObject(String hql, Object[] args) {
		return this.queryObject(hql, args,null);
	}

	/* (non-Javadoc)
	 * @see org.konghao.baisc.dao.IBaseDao#queryObject(java.lang.String, java.lang.Object)
	 */
	public Object queryObject(String hql, Object arg) {
		return this.queryObject(hql, new Object[]{arg});
	}

	/* (non-Javadoc)
	 * @see org.konghao.baisc.dao.IBaseDao#queryObject(java.lang.String)
	 */
	public Object queryObject(String hql) {
		return this.queryObject(hql,null);
	}

	/* (non-Javadoc)
	 * @see org.konghao.baisc.dao.IBaseDao#updateByHql(java.lang.String, java.lang.Object[])
	 */
	public void updateByHql(String hql, Object[] args) {
		Query query = getSession().createQuery(hql);
		setParameter(query, args);
		query.executeUpdate();
	}

	/* (non-Javadoc)
	 * @see org.konghao.baisc.dao.IBaseDao#updateByHql(java.lang.String, java.lang.Object)
	 */
	public void updateByHql(String hql, Object arg) {
		this.updateByHql(hql,new Object[]{arg});
	}

	/* (non-Javadoc)
	 * @see org.konghao.baisc.dao.IBaseDao#updateByHql(java.lang.String)
	 */
	public void updateByHql(String hql) {
		this.updateByHql(hql,null);
	}


	/* (non-Javadoc)
	 * @see org.konghao.baisc.dao.IBaseDao#listBySql(java.lang.String, java.lang.Object, java.lang.Class, boolean)
	 */
	public <N extends Object>List<N> listBySql(String sql, Object arg, Class<?> clz,
			boolean hasEntity) {
		return this.listBySql(sql, new Object[]{arg}, clz, hasEntity);
	}

	/* (non-Javadoc)
	 * @see org.konghao.baisc.dao.IBaseDao#listBySql(java.lang.String, java.lang.Class, boolean)
	 */
	public <N extends Object>List<N> listBySql(String sql, Class<?> clz, boolean hasEntity) {
		return this.listBySql(sql, null, clz, hasEntity);
	}


	public Object queryObject(String hql, Object[] args,
			Map<String, Object> alias) {
		Query query = getSession().createQuery(hql);
		setAliasParameter(query, alias);
		setParameter(query, args);
		return query.uniqueResult();
	}

	public Object queryObjectByAlias(String hql, Map<String, Object> alias) {
		return this.queryObject(hql,null,alias);
	}

	/*
	 * Cust BySql
	 */

	
	
	public List<T> queryListBySql(String sql, Object[] args, Class<?> clz) {
		SQLQuery query = getSession().createSQLQuery(sql).addEntity(clz);
		if (args != null)
			setParameter(query, args);
		List<T> t = query.list();
		return t;
	}


	public T queryObjectBySql(String sql, Object[] args, Class<?> clz) {
		SQLQuery query = getSession().createSQLQuery(sql).addEntity(clz);
		if (args != null)
			setParameter(query, args);
		List<T> t = query.list();
		return t.isEmpty() ? null : t.get(0);
	}

	public int addObjectBySql(String sql, Object[] args) {
		SQLQuery query = getSession().createSQLQuery(sql);
		if (args != null)
			setParameter(query, args);
		return query.executeUpdate();
	}
	
	public int delUpObjectBySql(String sql, Object[] args) {
		
		SQLQuery query = getSession().createSQLQuery(sql);
		if (args != null)
			setParameter(query, args);
		return query.executeUpdate();
	}
	
	public long queryTotal(String sql, Object[] args){
		SQLQuery query = getSession().createSQLQuery(sql);
		if (args != null)
			setParameter(query, args);
		long total = ((BigInteger) query.uniqueResult()).longValue();
		return total;
	}

}

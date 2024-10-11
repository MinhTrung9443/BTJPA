package vn.iotstar.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iotstar.config.JPAConfig;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.entity.User;

public class UserDaoImpl implements IUserDao{

	@Override
	public void insert(User user) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		
		try {
			trans.begin();
			//goi phuong thuc de insert update delete
			enma.persist(user);
			
			trans.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}finally {
			enma.close();
		}
	}

	@Override
	public void update(User user) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		
		try {
			trans.begin();
			//goi phuong thuc de insert update delete
			enma.merge(user);
			
			trans.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}finally {
			enma.close();
		}
	}

	@Override
	public void delete(int userid) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		
		try {
			trans.begin();
			//goi phuong thuc de insert update delete
			User user = enma.find(User.class, userid);
			if (user != null) {
				enma.remove(user);
			}else {
				throw new Exception("Khong tim thay");
			}
			
			trans.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}finally {
			enma.close();
		}
	}

	@Override
	public User findById(int userid)
	{
		EntityManager enma = JPAConfig.getEntityManager();
		User user = enma.find(User.class, userid);
		return user;
	}

	@Override
	public List<User> findAll(){
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<User> query = enma.createNamedQuery("User.findAll", User.class);
		return query.getResultList();
	}
	

	@Override
	public List<User> findByName(String name){
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT c FROM User c WHERE c.name like :name";
		TypedQuery<User> query = enma.createQuery(jpql, User.class);
		query.setParameter("catnam", "%" + name + "%");
		
		return query.getResultList();
	}

	@Override
	public List<User> findAll(int page, int pagesize){
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<User> query = enma.createNamedQuery("User.findAll", User.class);
		query.setFirstResult(page*pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}

	@Override
	public int count() {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT count(c) FROM User c";
		Query query = (Query) enma.createQuery(jpql);
		return ((Long)query.getSingleResult()).intValue();
		
	}
}

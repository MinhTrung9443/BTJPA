package vn.iotstar.service.impl;

import java.util.List;

import vn.iotstar.dao.IUserDao;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.entity.User;
import vn.iotstar.service.IUserService;

public class UserServiceImpl implements IUserService{
	IUserDao userdao = new UserDaoImpl();
	@Override
	public int count() {
		return userdao.count();
	}

	@Override
	public List<User> findAll(int page, int pagesize) {
		return userdao.findAll(page, pagesize);
	}

	@Override
	public List<User> findByName(String name) {
		return userdao.findByName(name);
	}

	@Override
	public List<User> findAll() {
		return userdao.findAll();
	}

	@Override
	public User findById(int userid) {
		return userdao.findById(userid);
	}

	@Override
	public void delete(int userid) throws Exception {
		userdao.delete(userid);
		
	}

	@Override
	public void update(User user) {
		userdao.update(user);
		
	}

	@Override
	public void insert(User user) {
		userdao.insert(user);
		
	}

}

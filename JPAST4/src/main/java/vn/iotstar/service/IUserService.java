package vn.iotstar.service;

import java.util.List;

import vn.iotstar.entity.User;

public interface IUserService {
	int count();

	List<User> findAll(int page, int pagesize);

	List<User> findByName(String name);

	List<User> findAll();

	User findById(int userid);

	void delete(int userid) throws Exception;

	void update(User user);

	void insert(User user);
}

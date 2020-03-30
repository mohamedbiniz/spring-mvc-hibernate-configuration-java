package com.tpseance4.services;

import java.util.List;

import com.tpseance4.dao.UserDAO;
import com.tpseance4.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public List<User> getUsers() {
		return userDAO.getUsers();
	}

	@Override
	public void saveUser(User theUser) {
		userDAO.saveUser(theUser);
	}

	@Override
	public User getUser(int theId) {
		return userDAO.getUser(theId);
	}

	@Override
	public void deleteUser(int theId) {
		userDAO.deleteUser(theId);
	}
}






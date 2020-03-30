package com.tpseance4.dao;

import com.tpseance4.entities.User;

import java.util.List;

public interface UserDAO {

	public List<User> getUsers();

	public void saveUser(User theUser);

	public User getUser(int theId);

	public void deleteUser(int theId);
	
}

package com.tpseance4.services;

import java.util.List;

import com.tpseance4.entities.User;

public interface UserService {

	public List<User> getUsers();

	public void saveUser(User theUser);

	public User getUser(int theId);

	public void deleteUser(int theId);
	
}

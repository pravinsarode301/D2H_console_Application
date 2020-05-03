/**
 * 
 */
package com.d2h.application.user.repo.cqrs.impl;

import java.util.Optional;

import com.d2h.application.databases.UserReadDB;
import com.d2h.application.user.model.User;
import com.d2h.application.user.repo.cqrs.UserQueryInterface;

/**
 * @author pravin sarode
 *
 */
public class UserQueryImpl implements UserQueryInterface {

	private UserReadDB dbService = UserReadDB.getInstance();


	@Override
	public boolean checkLogin(String username, String password) {
		return dbService.checkLogin(username, password);
	}

	@Override
	public Optional<User> getUser(String username, String password) {

		return dbService.getUser(username, password);
	}
	
	@Override
	public Optional<User> getUserById(String userId) {
		return dbService.getUserDetails(userId);
	}

}

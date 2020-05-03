/**
 * 
 */
package com.d2h.application.user.repo.cqrs.impl;

import java.util.Optional;

import com.d2h.application.databases.UserWriteDB;
import com.d2h.application.user.model.User;
import com.d2h.application.user.repo.cqrs.UserCommandInterface;

/**
 * @author pravin sarode
 *
 */
public class UserCommandImpl implements UserCommandInterface {

	private UserWriteDB dbService = UserWriteDB.getInstance();

	/**
	 * 
	 */
	public UserCommandImpl() {
		 
	}

	@Override
	public Optional<User> updateUser(User user) {

		return dbService.updateUser(user);
	}

}

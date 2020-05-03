/**
 * 
 */
package com.d2h.application.user.services.cqrs.impl;

import javax.activity.InvalidActivityException;

import com.d2h.application.user.model.User;
import com.d2h.application.user.repo.cqrs.UserQueryInterface;
import com.d2h.application.user.repo.cqrs.impl.UserQueryImpl;
import com.d2h.application.user.services.cqrs.UserQueryService;

/**
 * @author pravin sarode
 *
 */
public class UserQueryServiceImpl implements UserQueryService {

	private UserQueryInterface userQuery = new UserQueryImpl();
	private User user = null;

	public UserQueryServiceImpl(User user) {
		this.user = user;
	}

	public UserQueryServiceImpl() {
	}

	@Override
	public int checkBalance() throws InvalidActivityException {
		if (this.user == null) {
			throw new InvalidActivityException("inline user object is null, can't perform this operation");
		}
		return ((User) user).getBalance();
	}

	@Override
	public int checkBalance(User user) throws InvalidActivityException {
		if (user == null) {
			throw new InvalidActivityException("inline user object is null, can't perform this operation");
		}
		return ((User) user).getBalance();
	}

	@Override
	public User getUserDetails(String userName, String password) {
		return userQuery.getUser(userName, password).get();
	}

	@Override
	public void setUserEntityForService(User user) {
		this.user = user;
		
	}

}

/**
 * 
 */
package com.d2h.application.user.services.cqrs.impl;

import javax.activity.InvalidActivityException;

import com.d2h.application.user.model.User;
import com.d2h.application.user.repo.cqrs.UserCommandInterface;
import com.d2h.application.user.repo.cqrs.impl.UserCommandImpl;
import com.d2h.application.user.services.cqrs.UserCommandService;

/**
 * @author pravin sarode
 *
 */
public class UserCommandServiceImpl implements UserCommandService {

	private User user = null;

	private UserCommandInterface userCommand = new UserCommandImpl();
	
	public UserCommandServiceImpl(User user) {
		this.user = user;
	}

	@Override
	public boolean rechargeAccount(int amount) throws InvalidActivityException {
		if (user == null) {
			throw new InvalidActivityException("inline user object is null, can't perform this operation");
		}
		try {
			user.setBalance(user.getBalance() + amount);
			System.out.println("Recharge completed successfully. Current balance is " + user.getBalance());

			userCommand.updateUser(user);

		} catch (Exception e) {
			System.out.println("recharge failed");
			return false;
		}

		return true;
	}

	@Override
	public User updateUserdetails(User user) {
		try {
			System.out.println("Email and Phone updated successfully");
			return userCommand.updateUser(user).get();
		} catch (Exception e) {
			System.out.println("failed to update");
		}

		System.out.println(user.toString());
		return null;
	}

}

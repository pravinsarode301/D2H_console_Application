/**
 * 
 */
package com.d2h.application.databases.operation;

import java.util.Optional;

import com.d2h.application.user.model.User;

/**
 * @author pravin sarode
 *
 */
public interface UserWriteOperation {

	/**
	 * @param userId
	 * @param User
	 */
	void updateUser(String userId, User User);
	
	public Optional<User> updateUser(User user);

}

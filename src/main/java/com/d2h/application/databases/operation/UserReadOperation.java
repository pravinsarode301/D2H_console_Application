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
public interface UserReadOperation {

	/**
	 * @param userId
	 * @return
	 */
	Optional<User> getUserDetails(String userId);

	boolean checkLogin(String userId, String password);

	/**
	 * @param username
	 * @param password
	 * @return
	 */
	Optional<User> getUser(String username, String password);

}

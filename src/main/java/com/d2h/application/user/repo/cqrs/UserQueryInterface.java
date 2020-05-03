/**
 * 
 */
package com.d2h.application.user.repo.cqrs;

import java.util.Optional;

import com.d2h.application.user.model.User;

/**
 * @author pravt
 *
 */
public interface UserQueryInterface {
	
	
	boolean checkLogin(String username,String password);
	
	Optional<User> getUser(String username,String password);

	Optional<User> getUserById(String userId);

}

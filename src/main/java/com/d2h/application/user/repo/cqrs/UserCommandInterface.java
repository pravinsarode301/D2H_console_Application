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
public interface UserCommandInterface {
	
	Optional<User> updateUser(User user);

}

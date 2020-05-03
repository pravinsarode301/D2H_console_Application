/**
 * 
 */
package com.d2h.application.user.services.cqrs;

import javax.activity.InvalidActivityException;

import com.d2h.application.user.model.User;

/**
 * @author pravin sarode
 *
 */
public interface UserQueryService {

	int checkBalance() throws InvalidActivityException;

	int checkBalance(User user) throws InvalidActivityException;
	
	 User getUserDetails(String userName, String password);
	 
	 void setUserEntityForService(User user);

}

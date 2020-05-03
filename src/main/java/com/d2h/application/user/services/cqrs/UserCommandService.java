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
public interface UserCommandService {

	boolean rechargeAccount(int amount) throws InvalidActivityException;

	User updateUserdetails(User user);

}

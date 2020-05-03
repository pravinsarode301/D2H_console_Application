/**
 * 
 */
package com.d2h.application.login.services.impl;

import java.util.Optional;
import java.util.function.BiPredicate;

import com.d2h.application.login.services.LoginService;
import com.d2h.application.user.repo.cqrs.UserQueryInterface;
import com.d2h.application.user.repo.cqrs.impl.UserQueryImpl;

/**
 * @author pravt
 *
 */
public class Login implements LoginService {
	
	private UserQueryInterface userQuery = new UserQueryImpl();

	@Override
	public BiPredicate<Optional<String>, Optional<String>> doLogin() {
		return (userName, password) -> {
			//return UserRepository.users.stream()
				//	.anyMatch(x -> userName.get().equals(x.getUserName()) && password.get().equals(x.getPassword()));
			return userQuery.checkLogin(userName.get(), password.get());
			
		};
	}

}

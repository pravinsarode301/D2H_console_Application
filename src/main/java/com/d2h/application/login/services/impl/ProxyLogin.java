/**
 * 
 */
package com.d2h.application.login.services.impl;

import java.util.Optional;
import java.util.function.BiPredicate;

import com.d2h.application.login.services.LoginService;

/**
 * @author pravt
 *
 */
//Proxy Pattern
public class ProxyLogin implements LoginService {

	private LoginService loginService = new Login();

	@Override
	public BiPredicate<Optional<String>, Optional<String>> doLogin() {
		return (userName, password) -> {
			if (userName.isPresent() && password.isPresent()) {
				return loginService.doLogin().test(userName, password);
			}
			return false;
		};
	}
}
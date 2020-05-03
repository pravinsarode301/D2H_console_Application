/**
 * 
 */
package com.d2h.application.login.services;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

import com.d2h.application.user.model.User;

/**
 * @author pravt
 *
 */
public interface LoginService {

	public BiPredicate<Optional<String>, Optional<String>> doLogin();

}

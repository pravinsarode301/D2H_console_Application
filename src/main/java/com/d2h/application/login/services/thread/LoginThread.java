/**
 * 
 */
package com.d2h.application.login.services.thread;

import java.util.Optional;
import java.util.concurrent.Callable;

import com.d2h.application.login.services.LoginService;
import com.d2h.application.login.services.impl.ProxyLogin;

/**
 * @author pravt
 *
 */
public class LoginThread implements Callable<Boolean> {

	private String username;
	private String password;
	private LoginService loginService = new ProxyLogin();

	public LoginThread(String username, String password) {
		this.username = username;
		this.password = password;

	}
	
	public LoginThread() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}



	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}



	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}



	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	public Boolean call() throws Exception {
		if (!loginService.doLogin().test(Optional.ofNullable(username), Optional.ofNullable(password))) {
			System.out.println("wrong credientials combination, please try after sometime");
			return false;
		}
		System.out.println("Login successfull");
		return true;
	}

}

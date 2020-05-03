/**
 * 
 */
package com.d2h.application.databases;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.d2h.application.databases.operation.UserReadOperation;
import com.d2h.application.user.model.User;

/**
 * @author pravin sarode
 *
 */
public class UserReadDB implements Observer, UserReadOperation {

	private Map<String, User> readDB = new ConcurrentHashMap<>();

	private  static UserReadDB instance = null;

	private UserReadDB() {

	}

	/**
	 * @return the instance
	 */
	public  static UserReadDB getInstance() {
		if (instance == null) { // Single Checked
			synchronized (UserReadDB.class) {
				if (instance == null) { // Double checked
					instance = new UserReadDB();
				}
			}
		}
		return instance;
	}

	@Override
	public void update(Observable o, Object db) {
		this.readDB = (Map<String, User>) db;
	}

	@Override
	public Optional<User> getUserDetails(String userId) {
		return Optional.ofNullable(readDB.get(userId));

	}

	@Override
	public boolean checkLogin(String userName, String password) {

		return readDB.keySet().stream().map(x -> readDB.get(x))
				.anyMatch(x -> x.getUserName().equalsIgnoreCase(userName) && x.getPassword().equalsIgnoreCase(password));

	}

	/**
	 * @param username
	 * @param password
	 * @return
	 */
	@Override
	public Optional<User> getUser(String username, String password) {
		return readDB.keySet().stream().map(x -> readDB.get(x))
				.filter(x -> x.getUserName().equalsIgnoreCase(username) && x.getPassword().equalsIgnoreCase(password)).findAny();

	}

}

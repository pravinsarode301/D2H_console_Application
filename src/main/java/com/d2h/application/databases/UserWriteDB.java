/**
 * 
 */
package com.d2h.application.databases;

import java.util.Map;
import java.util.Observable;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.d2h.application.databases.operation.UserWriteOperation;
import com.d2h.application.user.model.User;

/**
 * @author pravin sarode
 *
 */
public class UserWriteDB extends Observable implements UserWriteOperation {

	private UserReadDB readDb = UserReadDB.getInstance();

	static private Map<String, User> writeDB = new ConcurrentHashMap<>();

	private static UserWriteDB instance = null;

	private UserWriteDB() {
		super.addObserver(readDb);
		updateToObserver();
	}

	/**
	 * @return the instance
	 */
	public static UserWriteDB getInstance() {
		if (instance == null) { // Single Checked
			synchronized (UserWriteDB.class) {
				if (instance == null) { // Double checked
					instance = new UserWriteDB();
				}
			}
		}
		return instance;
	}

	{
System.out.println("executed instance block");
		User user = null;
		for (int i = 1; i <= 3; i++) {
			UUID userId = UUID.randomUUID();
			user = new User.Builder().setUserName("admin" + i).setPassword("admin" + i).setUserId(userId.toString())
					.build();
			writeDB.put(String.valueOf(userId), user);
		}
		updateToObserver();
	}

	@Override
	public void updateUser(String userId, User user) {
		writeDB.put(userId.toString(), user);
		updateToObserver();
	}

	private void updateToObserver() {
		setChanged();
		notifyObservers(writeDB);
	}

	@Override
	public Optional<User> updateUser(User user) {
		writeDB.put(user.getUserId(), user);
		return Optional.ofNullable(writeDB.get(user.getUserId()));
	}
}

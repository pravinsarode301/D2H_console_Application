/**
 * 
 */
package com.d2h.application.databases;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;

import com.d2h.application.databases.operation.SubscriptionReadOperation;
import com.d2h.application.subscription.decorator.Subscription;

/**
 * @author pravin sarode
 *
 */
public class SubscriptionReadDB implements Observer, SubscriptionReadOperation {

	private  Map<String, Subscription> readDB = new ConcurrentHashMap<>();

	private  static SubscriptionReadDB instance = null;

	private SubscriptionReadDB() {

	}

	/**
	 * @return the instance
	 */
	public static SubscriptionReadDB getInstance() {
		if (instance == null) { // Single Checked
			synchronized (SubscriptionReadDB.class) {
				if (instance == null) { // Double checked
					instance = new SubscriptionReadDB();
				}
			}
		}
		return instance;
	}

	@Override
	public void update(Observable o, Object db) {
		this.readDB = (Map<String, Subscription>) db;
	}

	@Override
	public Subscription getSubscriptionDetails(String subscriptionId) {
		return readDB.get(subscriptionId);
	}

}

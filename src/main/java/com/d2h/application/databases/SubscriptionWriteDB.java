/**
 * 
 */
package com.d2h.application.databases;

import java.util.Map;
import java.util.Observable;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.d2h.application.databases.operation.SubscriptionWriteOperation;
import com.d2h.application.subscription.decorator.Subscription;

/**
 * @author pravin sarode
 *
 */
public class SubscriptionWriteDB extends Observable implements SubscriptionWriteOperation {

	private SubscriptionReadDB readDb = SubscriptionReadDB.getInstance();

	private Map<String, Subscription> writeDB = new ConcurrentHashMap<>();

	private Map<String, Subscription> unsubscribeSubscriptions = new ConcurrentHashMap<>();

	private static SubscriptionWriteDB instance = null;

	private SubscriptionWriteDB() {
		super.addObserver(readDb);
	}

	/**
	 * @return the instance
	 */
	public static SubscriptionWriteDB getInstance() {
		if (instance == null) { // Single Checked
			synchronized (SubscriptionWriteDB.class) {
				if (instance == null) { // Double checked
					instance = new SubscriptionWriteDB();
				}
			}
		}
		return instance;
	}

	@Override
	public String saveSubscription(Subscription subscription) {
		UUID subscriptionId = UUID.randomUUID();
		writeDB.put(subscriptionId.toString(), subscription);
		updateToObserver();
		return subscriptionId.toString();
	}

	@Override
	public void updateSubscription(String subscriptionId, Subscription subscription) {
		writeDB.put(subscriptionId.toString(), subscription);
		updateToObserver();
	}

	@Override
	public void deactivateService(String subscriptionId) {
		unsubscribeSubscriptions.put(subscriptionId, writeDB.get(subscriptionId));
		writeDB.remove(subscriptionId);
	}

	private void updateToObserver() {
		setChanged();
		notifyObservers(writeDB);
	}
}

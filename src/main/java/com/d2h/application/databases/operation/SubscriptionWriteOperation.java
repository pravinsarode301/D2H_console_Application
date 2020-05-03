/**
 * 
 */
package com.d2h.application.databases.operation;

import com.d2h.application.subscription.decorator.Subscription;

/**
 * @author pravin sarode
 *
 */
public interface SubscriptionWriteOperation {

	/**
	 * @param subscriptionId
	 */
	void deactivateService(String subscriptionId);

	/**
	 * @param subscription
	 */
	String saveSubscription(Subscription subscription);

	/**
	 * @param subscriptionId
	 * @param subscription
	 */
	void updateSubscription(String subscriptionId, Subscription subscription);

}

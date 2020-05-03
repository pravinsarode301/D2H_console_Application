/**
 * 
 */
package com.d2h.application.subscription.repo.cqrs.impl;

import com.d2h.application.databases.SubscriptionReadDB;
import com.d2h.application.subscription.decorator.Subscription;
import com.d2h.application.subscription.repo.cqrs.SubscriptionQueryInterface;

/**
 * @author pravin sarode
 *
 */
public class SubscriptionQueryInterfaceImpl implements SubscriptionQueryInterface {

	private SubscriptionReadDB readDbService = SubscriptionReadDB.getInstance();

	@Override
	public Subscription getSubscriptionDetails(String subscriptionId) {
		return readDbService.getSubscriptionDetails(subscriptionId);
	}

	@Override
	public Subscription viewSubscriptionHistory(String subscriptionId) {
		return readDbService.getSubscriptionDetails(subscriptionId);
		

	}

}

/**
 * 
 */
package com.d2h.application.subscription.repo.cqrs.impl;

import com.d2h.application.databases.SubscriptionWriteDB;
import com.d2h.application.subscription.decorator.Subscription;
import com.d2h.application.subscription.repo.cqrs.SubscriptionCommandInterface;

/**
 * @author pravin sarode
 *
 */
public class SubscriptionCommandInterfaceImpl implements SubscriptionCommandInterface {

	private SubscriptionWriteDB writeDbService = SubscriptionWriteDB.getInstance();

	@Override
	public String saveSubscription(Subscription subscription) {
		return writeDbService.saveSubscription(subscription);

	}

	@Override
	public void updateSubscription(String subscriptionId, Subscription subscription) {
		writeDbService.updateSubscription(subscriptionId, subscription);

	}

	@Override
	public void deactivateService(String subscriptionId) {
		writeDbService.deactivateService(subscriptionId);

	}

}

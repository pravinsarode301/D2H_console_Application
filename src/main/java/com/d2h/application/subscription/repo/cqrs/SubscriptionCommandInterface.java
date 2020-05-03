/**
 * 
 */
package com.d2h.application.subscription.repo.cqrs;

import com.d2h.application.subscription.decorator.Subscription;

/**
 * @author pravin sarode
 *
 */
public interface SubscriptionCommandInterface {

	public String saveSubscription(Subscription subscription);

	public void updateSubscription(String subscriptionId, Subscription subscription);

	public void deactivateService(String subscriptionId);

}

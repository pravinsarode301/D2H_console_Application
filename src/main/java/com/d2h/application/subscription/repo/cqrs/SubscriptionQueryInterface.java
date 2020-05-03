/**
 * 
 */
package com.d2h.application.subscription.repo.cqrs;

import com.d2h.application.subscription.decorator.Subscription;

/**
 * @author pravin sarode
 *
 */
public interface SubscriptionQueryInterface {
	
	Subscription getSubscriptionDetails(String subscriptionId);
	
	Subscription viewSubscriptionHistory(String subscriptionId);
}

/**
 * 
 */
package com.d2h.application.subscription.services.cqrs;

/**
 * @author pravin sarode
 *
 */
public interface SubscriptionQueryService {

	
	void viewSubscriptionHistory(String subscriptionId);

	void viewCurrentSubscriptionDetails(String subscriptionId);
}

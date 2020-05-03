/**
 * 
 */
package com.d2h.application.databases.operation;

import com.d2h.application.subscription.decorator.Subscription;

/**
 * @author pravin sarode
 *
 */
public interface SubscriptionReadOperation {

	Subscription getSubscriptionDetails(String subscriptionId);

}

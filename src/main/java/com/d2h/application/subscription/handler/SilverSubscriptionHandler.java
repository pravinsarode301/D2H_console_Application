/**
 * 
 */
package com.d2h.application.subscription.handler;

import com.d2h.application.subscription.decorator.Subscription;
import com.d2h.application.subscription.decorator.impl.SilverSubscription;

/**
 * @author pravin sarode
 *
 */
public class SilverSubscriptionHandler extends RequestHandler {


	@Override
	public RequestHandler nextHandler() {
		return null;
	}

	@Override
	public Subscription processRequest(String choice) throws Exception {

		if ("S".equalsIgnoreCase(choice)) {
			return new SilverSubscription();
		}

		throw new Exception("invalid choise, please make proper choise to continue");
	}
}

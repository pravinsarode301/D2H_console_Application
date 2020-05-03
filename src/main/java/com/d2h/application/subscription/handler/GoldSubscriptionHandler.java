/**
 * 
 */
package com.d2h.application.subscription.handler;

import com.d2h.application.subscription.decorator.Subscription;
import com.d2h.application.subscription.decorator.impl.GoldSubscription;

/**
 * @author pravin sarode
 *
 */
public class GoldSubscriptionHandler extends RequestHandler {

	private RequestHandler handler;

	@Override
	public RequestHandler nextHandler() {
		handler = new SilverSubscriptionHandler();
		return handler;
	}

	@Override
	public Subscription processRequest(String choice) throws Exception {

		if ("G".equalsIgnoreCase(choice)) {
			return new GoldSubscription();
		}

		return this.nextHandler().processRequest(choice);

	}

}

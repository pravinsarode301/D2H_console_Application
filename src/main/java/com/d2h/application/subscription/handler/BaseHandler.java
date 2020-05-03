/**
 * 
 */
package com.d2h.application.subscription.handler;

import com.d2h.application.subscription.decorator.Subscription;
import com.d2h.application.subscription.decorator.impl.BaseSubscription;

/**
 * @author pravin sarode
 *
 */
public class BaseHandler extends RequestHandler {

	private RequestHandler handler;

	@Override
	public RequestHandler nextHandler() {
		handler = new GoldSubscriptionHandler();
		return handler;
	}

	@Override
	public Subscription processRequest(String choice) throws Exception {

		if ("B".equalsIgnoreCase(choice)) {
			return new BaseSubscription();
		}
		return this.nextHandler().processRequest(choice);
	}

}

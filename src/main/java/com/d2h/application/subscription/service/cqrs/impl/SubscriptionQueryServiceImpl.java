/**
 * 
 */
package com.d2h.application.subscription.service.cqrs.impl;

import com.d2h.application.subscription.decorator.Subscription;
import com.d2h.application.subscription.model.CompositeBill;
import com.d2h.application.subscription.repo.cqrs.SubscriptionQueryInterface;
import com.d2h.application.subscription.repo.cqrs.impl.SubscriptionQueryInterfaceImpl;
import com.d2h.application.subscription.services.cqrs.SubscriptionQueryService;

/**
 * @author pravin sarode
 *
 */
public class SubscriptionQueryServiceImpl implements SubscriptionQueryService {

	private Subscription subscription = null;

	private SubscriptionQueryInterface query = new SubscriptionQueryInterfaceImpl();

	@Override
	public void viewSubscriptionHistory(String subscriptionId) {

		subscription = query.viewSubscriptionHistory(subscriptionId);

		if (subscription.getBills().getBills().size() > 0) {
			System.out.println(subscription.getBills().getBills().peek().toString());
		} else {
			System.out.println("Currently you have not subscribe to any channel");
		}

	}

	@Override
	public void viewCurrentSubscriptionDetails(String subscriptionId) {
		subscription = query.viewSubscriptionHistory(subscriptionId);

		subscription.getBills().getBills().stream().forEach(x -> {

			System.out.println(x);
			System.out.println();
		});

		CompositeBill bill = subscription.getBills();
		System.out.println("Total Amount calculated : " + bill.showPrice());
	}

}

/**
 * 
 */
package com.d2h.application.subscription.service.cqrs.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.function.BiPredicate;

import com.d2h.application.subscription.decorator.Subscription;
import com.d2h.application.subscription.decorator.impl.ChannelsDecorator;
import com.d2h.application.subscription.decorator.impl.ServicesDecorator;
import com.d2h.application.subscription.handler.BaseHandler;
import com.d2h.application.subscription.handler.RequestHandler;
import com.d2h.application.subscription.model.Channel;
import com.d2h.application.subscription.model.Services;
import com.d2h.application.subscription.repo.cqrs.SubscriptionCommandInterface;
import com.d2h.application.subscription.repo.cqrs.SubscriptionQueryInterface;
import com.d2h.application.subscription.repo.cqrs.impl.SubscriptionCommandInterfaceImpl;
import com.d2h.application.subscription.repo.cqrs.impl.SubscriptionQueryInterfaceImpl;
import com.d2h.application.subscription.services.cqrs.SubscriptionCommandService;
import com.d2h.application.user.model.User;

/**
 * @author pravin sarode
 *
 */
public class SubscriptionCommandServiceImpl implements SubscriptionCommandService {

	private Subscription subscription = null;
	private RequestHandler handler = new BaseHandler();

	private SubscriptionQueryInterface query = new SubscriptionQueryInterfaceImpl();
	private SubscriptionCommandInterface command = new SubscriptionCommandInterfaceImpl();

	@Override
	public Subscription subscribe(String choice, Integer duration, User user) throws Exception {
		if (choice.isEmpty() || duration == 0 || user == null) {
			throw new NullPointerException("values can't be null");
		}

		// try {
		subscription = subscriptionFactory(choice);

		BiPredicate<Integer, Integer> biPredicate = subscription.isEligiableToSubscribe();

		if (!biPredicate.test(duration, user.getBalance())) {
			System.out.println("account balance is low, refill the same in order to subcribe the plan");
			throw new Exception("account balance is low, refill the same in order to subcribe the plan");
		}

		subscription.initialiseSubscription();
		String subscriptionId = command.saveSubscription(subscription);

		user.setSubscriptionId(subscriptionId);
		user.setBalance(user.getBalance() - subscription.getMonthlyCharges());
		// user.getLogs().add(subscription.getBills().getBills().peek());

		System.out.println("You have successfully subscribed the pack -" + subscription.subscriptionName + " id: "
				+ subscriptionId);
		System.out.println("Email notification sent successfully.\nSMS notification sent successfully .");
		user.notify("Email and SMS received at user account!");

		return subscription;
	}

	@Override
	public Subscription addChannelsToSubscription(Collection<Channel> channels, User user) throws Exception {
		subscription = query.getSubscriptionDetails(user.getSubscriptionId());
		subscription = new ChannelsDecorator(new HashSet<>(channels), subscription);
		BiPredicate<Integer, Integer> biPredicate = subscription.isEligiableToSubscribe();

		if (!biPredicate.test(1, user.getBalance())) {
			System.out.println("account balance is low, refill the same in order to subcribe the plan");
			throw new Exception("account balance is low, refill the same in order to subcribe the plan");
		}

		subscription.initialiseSubscription();
		command.updateSubscription(user.getSubscriptionId(), subscription);
		// update user account balance
		user.setBalance(user.getBalance() - channels.parallelStream().mapToInt(x -> x.getBaseCharges()).sum());
		user.setCurrentSubscription(subscription);
		System.out.println("channels are added to current subscription, user balance updated");

		return subscription;
	}

	@Override
	public Subscription addSpecialServicesToSubscription(List<Services> servicesList, User user) throws Exception {
		subscription = query.getSubscriptionDetails(user.getSubscriptionId());
		subscription = new ServicesDecorator(new HashSet<>(servicesList), subscription);

		BiPredicate<Integer, Integer> biPredicate = subscription.isEligiableToSubscribe();

		if (!biPredicate.test(1, user.getBalance())) {
			System.out.println("account balance is low, refill the same in order to subcribe the plan");
			throw new Exception("account balance is low, refill the same in order to subcribe the plan");
		}

		subscription.initialiseSubscription();
		command.updateSubscription(user.getSubscriptionId(), subscription);

		// update user account balance
		user.setBalance(user.getBalance() - servicesList.parallelStream().mapToInt(x -> x.getBaseCharges()).sum());
		user.setCurrentSubscription(subscription);
		System.out.println("service are added to current subscription, user balance updated");

		return subscription;
	}

	// factory pattern
	private Subscription subscriptionFactory(String choice) throws Exception {
		// chain of responsibility pattern
		return handler.processRequest(choice);
	}

}

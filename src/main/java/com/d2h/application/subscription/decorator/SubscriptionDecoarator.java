/**
 * 
 */
package com.d2h.application.subscription.decorator;

import java.util.Collection;

import com.d2h.application.subscription.model.Channel;
import com.d2h.application.subscription.model.Services;

/**
 * @author pravt
 *
 */
//Decorator handler for decorator object
public abstract class SubscriptionDecoarator extends Subscription {

	protected Subscription subscription = null;

	/**
	 * 
	 */
	public SubscriptionDecoarator(Subscription subscription) {
		this.subscription = subscription;
		cloneSubscription(this.subscription);
	}

	void cloneSubscription(Subscription subscription) {
		super.totalFair = subscription.totalFair;
		super.note = subscription.note;
		super.subscriptionName = subscription.subscriptionName;
		super.totalFair = subscription.getTotalFair();
		super.addServices(subscription.getServicesList());
		super.addVarChannels(subscription.getVarChannelsList());
		super.addChannels(subscription.getChannelsList());
		super.addBills(subscription.getBills());
	}

	@Override
	public void addVarChannels(Collection<Channel> channels) {
		super.addVarChannels(channels);
	}

	//composite pattern
	@Override
	public void setTotalFair(int fair) {
		super.totalFair = fair;
	}

	@Override
	public void addServices(Collection<Services> services) {
		super.addServices(services);
	}

	
	
	
}

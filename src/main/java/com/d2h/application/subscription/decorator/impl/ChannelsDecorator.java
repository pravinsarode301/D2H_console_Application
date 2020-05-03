/**
 * 
 */
package com.d2h.application.subscription.decorator.impl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiPredicate;

import com.d2h.application.subscription.decorator.Subscription;
import com.d2h.application.subscription.decorator.SubscriptionDecoarator;
import com.d2h.application.subscription.model.Bill;
import com.d2h.application.subscription.model.Channel;
import com.d2h.application.subscription.utils.SubscriptionUtils;

/**
 * @author pravin sarode
 *
 */
public class ChannelsDecorator extends SubscriptionDecoarator {

	private Collection<Channel> channels = new HashSet<>();
	private int discount;
	private int duration;
	private int subscriptionAmount;
	private int amountAfterDiscountApply;
	public int monthlyCharges = 100;
	private String subscriptionName = "VAR_ADDED_CHANNEL";

	/**
	 * 
	 */
	public ChannelsDecorator(final Channel channel, Subscription subscription) {
		this(subscription);
		channels.add(channel);
		System.out.println("channel are added");
	}

	public ChannelsDecorator(final Set<Channel> channels, Subscription subscription) {
		this(subscription);
		this.channels = channels;
	}

	private ChannelsDecorator(Subscription subscription) {
		super(subscription);
	}

	@Override
	public Integer calaculateCharges() {
		return channels.stream().mapToInt(x -> x.getBaseCharges()).sum();
	}

	@Override
	public Integer getMonthlyCharges() {
		return monthlyCharges;
	}

	@Override
	public void initialiseSubscription() {
		addVarChannels(channels);
		//super.subscriptionName = channels.stream().map(x -> new String(x.getName())).reduce("",	(a, b) -> a + ", " + b);
		super.subscriptionName = this.subscriptionName;
		this.monthlyCharges = calaculateCharges();
		//default setup
		discount = SubscriptionUtils.calculateDiscount(monthlyCharges, duration);
		subscriptionAmount = monthlyCharges * duration;
		amountAfterDiscountApply = SubscriptionUtils.applyDiscount(subscriptionAmount, discount);
		setTotalFair(this.totalFair + amountAfterDiscountApply);
//		super.getBills().push(generateBill());
		super.getBills().getBills().push(generateBill());
	}

	@Override
	public void addVarChannels(Collection<Channel> channels) {
		super.addVarChannels(channels);
	}

	@Override
	public BiPredicate<Integer, Integer> isEligiableToSubscribe() throws Exception {
		return (duration, userBalance) -> {
			this.duration = duration;

			discount = SubscriptionUtils.calculateDiscount(monthlyCharges, duration);
			subscriptionAmount = monthlyCharges * duration;
			amountAfterDiscountApply = SubscriptionUtils.applyDiscount(subscriptionAmount, discount);
			
			if (isEligiableToSubscribe.test(amountAfterDiscountApply, userBalance)) {
				return false;
			}
			return true;
		};
	}

	BiPredicate<Integer, Integer> isEligiableToSubscribe = (x, y) -> x > y ? true : false;

	@Override
	protected Bill generateBill() {

		return new Bill.Builder().packageName(this.subscriptionName).accountBalance(this.getTotalFair()).setChannelList(new HashSet<>(channels))
				.monthlyPrice(monthlyCharges).discountApplied(discount).noOfMonthsFor(duration)
				.finalPriceAfterDiscount(amountAfterDiscountApply).buyAt(LocalDateTime.now())
				.validTill(LocalDateTime.now().plusMonths(duration)).subscriptionAmount(subscriptionAmount).build();

	}

}

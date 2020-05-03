/**
 * 
 */
package com.d2h.application.subscription.decorator.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiPredicate;

import com.d2h.application.subscription.decorator.Subscription;
import com.d2h.application.subscription.model.Bill;
import com.d2h.application.subscription.model.Channel;
import com.d2h.application.subscription.utils.SubscriptionUtils;

/**
 * @author pravt
 *
 */
public class SilverSubscription extends Subscription {

	public final static int monthlyCharges = 50;
	public final static String note = monthlyCharges + " rs. monthly charges added";

	List<Channel> channels = Arrays.asList(new Channel("Star Sport1", 0), new Channel("Star Sport2", 0),
			new Channel("Star Sport3", 0));
	Set<Channel> defaultChannels = new HashSet<>(channels);

	private final String SUBSCRIPTION_NAME = "SILVER";

	private int discount;
	private int duration;
	private int subscriptionAmount;
	private int amountAfterDiscountApply;

	public SilverSubscription() {
		super.subscriptionName = SUBSCRIPTION_NAME;
		super.note = note;
		// initialiseSubscription();
	}

	@Override
	public void getinfo() {
		super.getinfo();
	}

	@Override
	public Integer calaculateCharges() {
		return defaultChannels.stream().mapToInt(x -> x.getBaseCharges()).sum();
	}

	@Override
	public Integer getMonthlyCharges() {
		return monthlyCharges;
	}

	@Override
	public void setTotalFair(int fair) {
		this.totalFair = fair;

	}

	@Override
	public void initialiseSubscription() {

		super.addChannels(defaultChannels);
		//super.getBills().push(generateBill());
		super.getBills().getBills().push(generateBill());
		System.out.println(SUBSCRIPTION_NAME + " subscription initialise successfully");
	}

	@Override
	public BiPredicate<Integer, Integer> isEligiableToSubscribe() throws Exception {
		return (duration, userBalance) -> {
			this.duration = duration;

			discount = SubscriptionUtils.calculateDiscount(monthlyCharges, duration);
			subscriptionAmount = monthlyCharges * duration;
			amountAfterDiscountApply = SubscriptionUtils.applyDiscount(subscriptionAmount, discount);
			setTotalFair(amountAfterDiscountApply);
			if (isEligiableToSubscribe.test(amountAfterDiscountApply, userBalance)) {
				return false;
			}
			return true;
		};
	}

	BiPredicate<Integer, Integer> isEligiableToSubscribe = (x, y) -> x > y ? true : false;

	@Override
	protected Bill generateBill() {

		return new Bill.Builder().packageName(this.subscriptionName)
				.accountBalance(amountAfterDiscountApply).monthlyPrice(monthlyCharges).discountApplied(discount).setChannelList(this.defaultChannels)
				.noOfMonthsFor(duration).finalPriceAfterDiscount(amountAfterDiscountApply).buyAt(LocalDateTime.now())
				.validTill(LocalDateTime.now().plusMonths(duration)).subscriptionAmount(subscriptionAmount).build();

	}
}

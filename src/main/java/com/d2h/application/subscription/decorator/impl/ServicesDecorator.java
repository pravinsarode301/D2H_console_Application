/**
 * 
 */
package com.d2h.application.subscription.decorator.impl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.function.BiPredicate;

import com.d2h.application.subscription.decorator.Subscription;
import com.d2h.application.subscription.decorator.SubscriptionDecoarator;
import com.d2h.application.subscription.model.Bill;
import com.d2h.application.subscription.model.Services;
import com.d2h.application.subscription.utils.SubscriptionUtils;

/**
 * @author pravin sarode
 *
 */
public class ServicesDecorator extends SubscriptionDecoarator {

	private Collection<Services> services = new HashSet<>();
	private int discount;
	private int duration;
	private int subscriptionAmount;
	private int amountAfterDiscountApply;
	private int monthlyCharges = 100;
	private String subscriptionName = "VAR_ADDED_SERVICES";


	public ServicesDecorator(Services service, Subscription subscription) {
		this(subscription);
		services.add(service);
		System.out.println("services are added");
	}

	public ServicesDecorator(Collection<Services> services, Subscription subscription) {
		this(subscription);
		this.services = services;
	}

	private ServicesDecorator(Subscription subscription) {
		super(subscription);
	}

	@Override
	public void setTotalFair(int fair) {
		super.setTotalFair(fair);
	}

	
	@Override
	public void getinfo() {
		super.getinfo();
	}

	@Override
	public Integer calaculateCharges() {
		return services.stream().mapToInt(x -> x.getBaseCharges()).sum();
	}

	@Override
	public Integer getMonthlyCharges() {
		return services.stream().mapToInt(x -> x.getBaseCharges()).sum();
	}

	@Override
	public void initialiseSubscription() {
		this.addServices(services);
		super.subscriptionName = this.subscriptionName;
		this.monthlyCharges = calaculateCharges();
		//default setup
		discount = SubscriptionUtils.calculateDiscount(monthlyCharges, duration);
		subscriptionAmount = monthlyCharges * duration;
		amountAfterDiscountApply = SubscriptionUtils.applyDiscount(subscriptionAmount, discount);
		setTotalFair(this.totalFair + amountAfterDiscountApply);
		//super.getBills().push(generateBill());
		super.getBills().getBills().push(generateBill());
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

		return new Bill.Builder().packageName(this.subscriptionName).accountBalance(this.getTotalFair()).setServiceList(new HashSet<>(services))
				.monthlyPrice(monthlyCharges).discountApplied(discount).noOfMonthsFor(duration)
				.finalPriceAfterDiscount(amountAfterDiscountApply).buyAt(LocalDateTime.now())
				.validTill(LocalDateTime.now().plusMonths(duration)).subscriptionAmount(subscriptionAmount).build();
		
	}

	
	
}

/**
 * 
 */
package com.d2h.application.subscription.utils;

/**
 * @author pravin sarode
 *
 */
public interface SubscriptionUtils {

	public static int calculatePercentage(int obtained, int total) {
		return obtained * total / 100;

	}

	public static int calculateDiscount(Integer charge, Integer duration) {
		if (duration >= 3) {
			int discount = calculatePercentage(10, (charge * duration));
			System.out.println("Discount applied : " + discount + " Rs.");
			return discount;
		}
		return 0;
	}
	
	public static int applyDiscount(int subscriptionAmount, int discount) {
		return subscriptionAmount - discount;
	}
}

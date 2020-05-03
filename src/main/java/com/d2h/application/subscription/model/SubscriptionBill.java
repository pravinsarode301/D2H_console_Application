/**
 * 
 */
package com.d2h.application.subscription.model;

/**
 * @author pravin sarode
 *
 */
public abstract class SubscriptionBill {

	String name;
	abstract int showPrice();
	
	/**
	 * 
	 */
	public SubscriptionBill() {
		// TODO Auto-generated constructor stub
	}
	public SubscriptionBill(String str) {
		this.name =str;
	}
}

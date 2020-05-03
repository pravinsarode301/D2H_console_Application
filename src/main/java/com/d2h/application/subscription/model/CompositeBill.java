/**
 * 
 */
package com.d2h.application.subscription.model;

import java.util.Stack;
import java.util.function.Predicate;

/**
 * @author pravin sarode
 *
 */
public class CompositeBill extends SubscriptionBill {

	/**
	 * @param str
	 */
	public CompositeBill(String str) {
		super(str);
	}
	
	/**
	 * 
	 */
	public CompositeBill() {
		// TODO Auto-generated constructor stub
	}

	// private List<SubscriptionBill> bills = new ArrayList<>();
	private Stack<SubscriptionBill> bills = new Stack<SubscriptionBill>();

	/**
	 * @return the bills
	 */
	public Stack<SubscriptionBill> getBills() {
		return bills;
	}

	/**
	 * @param bills the bills to set
	 */
	public void setBills(Stack<SubscriptionBill> bills) {
		this.bills = bills;
	}

	@Override
	public int showPrice() {
		System.out.println("****************************");
		System.out.println("Total no. of bills :" + bills.size());
		
		showVarChannelCost();
		showServicesCost();
		showPackageCost();

		return bills.stream().mapToInt(bill -> bill.showPrice()).sum();
	}

	private void showVarChannelCost() {
		System.out.println("Total value added channel cost :" + bills.stream().filter(var_channels_predicate).mapToInt(bill -> bill.showPrice()).sum());
	}

	private void showServicesCost() {
		System.out.println("Total value added services cost :" + bills.stream().filter(services_predicate).mapToInt(bill -> bill.showPrice()).sum());
	}

	private void showPackageCost() {
		//System.out.println("Package cost : " + bills.stream().filter(var_channels_predicate.negate()).filter(services_predicate.negate()).mapToInt(bill -> bill.showPrice()).sum());
		System.out.println("Package cost : " + bills.stream().filter(package_predicate).mapToInt(bill -> bill.showPrice()).sum());
	}

	public Predicate<SubscriptionBill> var_channels_predicate = x -> x.name.equalsIgnoreCase("VAR_ADDED_CHANNEL");
	public Predicate<SubscriptionBill> services_predicate = x -> x.name.equalsIgnoreCase("VAR_ADDED_SERVICES");
	public Predicate<SubscriptionBill> package_predicate = services_predicate.negate().and(var_channels_predicate.negate());
}

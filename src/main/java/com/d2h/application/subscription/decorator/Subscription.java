/**
 * 
 */
package com.d2h.application.subscription.decorator;

import java.awt.Composite;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiPredicate;

import com.d2h.application.subscription.model.Bill;
import com.d2h.application.subscription.model.Channel;
import com.d2h.application.subscription.model.CompositeBill;
import com.d2h.application.subscription.model.Services;
import com.d2h.application.subscription.utils.SubscriptionUtils;

/**
 * @author pravt
 *
 */
public abstract class Subscription implements SubscriptionUtils {

	protected int totalFair = 0;

	public String subscriptionName;
	public String note;

	// default channel list
	private final Set<Channel> channelsList = ConcurrentHashMap.newKeySet();

	// value added channel list
	private final Set<Channel> varChannelsList = ConcurrentHashMap.newKeySet();

	// services added
	private final Set<Services> servicesList = new HashSet<>();

	//private final Stack<PackageBill> billList = new Stack<PackageBill>();
	
	private CompositeBill  billList = new CompositeBill();
	
	public void getinfo() {

	}

	public abstract Integer calaculateCharges();

	public abstract Integer getMonthlyCharges();

	protected void addChannels(Collection<Channel> channels) {
		channels.stream().forEach(channel -> channelsList.add(channel));
	}

	protected void addVarChannels(Collection<Channel> channels) {
		channels.stream().forEach(varChannel -> varChannelsList.add(varChannel));
	}

	protected void addServices(Collection<Services> services) {
		services.stream().forEach(service -> servicesList.add(service));
	}
//	
//	protected void addBills(Collection<CompositeBill> bills) {
//		bills.stream().forEach(bill -> billList.getBills().add(bill));
//	}
	

	protected void addBills(CompositeBill bills) {
	//	bills.stream().forEach(bill -> billList.getBills().add(bill));
		this.billList = bills;
	}

	public int getTotalFair() {
		return totalFair;
	}

	public abstract void setTotalFair(int fair);

	// Method template pattern
	public abstract void initialiseSubscription();

	public Set<Channel> getChannelsList() {
		return channelsList;
	}

	public Set<Services> getServicesList() {
		return servicesList;
	}

	public CompositeBill getBills() {
		return billList;
	}

	public Set<Channel> getVarChannelsList() {
		return varChannelsList;
	}

	public abstract BiPredicate<Integer, Integer> isEligiableToSubscribe() throws Exception;

	protected abstract Bill generateBill();
	
	

}

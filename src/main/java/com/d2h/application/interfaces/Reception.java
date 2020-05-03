/**
 * 
 */
package com.d2h.application.interfaces;

import javax.activity.InvalidActivityException;

/**
 * @author pravin sarode
 *
 */
public interface Reception {

	void login() throws Exception;

	void startTV();

	void doAssist() throws Exception;

	void showCurrentlBalace() throws InvalidActivityException;

	void showAvailablePacks();

	void addChannelsToSubscription() throws InvalidActivityException;

	void addSpecialServicesToSubscription() throws InvalidActivityException;

	void doSubscribePack() throws InvalidActivityException, Exception;

	void rechargeAccount() throws InvalidActivityException;

	void viewCurrentSubscriptionDetails() throws Exception;
	
	void updateUserDeatils() throws Exception;
	
	void viewSubscriptionHistory() throws Exception;
	
	void logout() throws Exception;

	/**
	 * 
	 */
	void showMyProfile();
}

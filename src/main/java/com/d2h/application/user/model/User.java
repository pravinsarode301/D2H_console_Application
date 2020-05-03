/**
 * 
 */
package com.d2h.application.user.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.d2h.application.subscription.decorator.Subscription;
import com.d2h.application.subscription.model.Bill;

/**
 * @author pravt
 *
 */
//builder patter for fluent api
public class User implements Notifiable{

	private String userId;
	private String userName;
	private String password;
	private String phoneNumber;
	private String address;
	private int balance;
	private String firstName;
	private String lastName;
	private Subscription currentSubscription;
	private List<Bill> subscriptionLogs = new ArrayList<>();
	private String subscriptionId;

	private User(Builder builder) {

		this.userName = builder.userName;
		this.password = builder.password;
		this.phoneNumber = builder.phoneNumber;
		this.address = builder.address;
		this.balance = builder.balance;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.currentSubscription = builder.currentSubscription;
		this.userId = builder.userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public int getBalance() {
		return balance;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	

	@Override
	public String toString() {
		return String.format(
				"My Profile \n> userId = %s\n> userName = %s\n> password = %s\n> phoneNumber = %s\n> address = %s\n> balance = %s\n> firstName = %s\n> lastName = %s\n> currentSubscription = %s\n> subscriptionLogs = %s\n> subscriptionId=%s",
				userId, userName, password, phoneNumber, address, balance, firstName, lastName, currentSubscription,
				subscriptionLogs, subscriptionId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(phoneNumber, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		return Objects.equals(phoneNumber, other.phoneNumber) && Objects.equals(userName, other.userName);
	}

	public static class Builder {
		public String userId;
		private String userName;
		private String password;
		private String phoneNumber;
		private String address;
		private int balance = 1000;
		private String firstName;
		private String lastName;
		private Subscription currentSubscription;

		public Builder setUserName(String userName) {
			this.userName = userName;
			return this;
		}

		public Builder setPassword(String password) {
			this.password = password;
			return this;
		}

		public Builder setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}

		public Builder setAddress(String address) {
			this.address = address;
			return this;
		}

		public Builder setBalance(int balance) {
			this.balance = balance;
			return this;
		}

		public Builder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder setLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder setSubscription(Subscription subscription) {
			this.currentSubscription = subscription;
			return this;
		}
		
		public Builder setUserId(String userId) {
			this.userId = userId;
			return this;
		}

		public User build() {
			return new User(this);
		}
	}

	@Override
	public void notify(String messagee) {
		Notifiable.super.notify(messagee);
	}

	public Subscription getCurrentSubscription() {
		return currentSubscription;
	}

	public void setCurrentSubscription(Subscription currentSubscription) {
		this.currentSubscription = currentSubscription;
	}

	public List<Bill> getLogs() {
		return subscriptionLogs;
	}

	public void setLogs(List<Bill> logs) {
		this.subscriptionLogs = logs;
	}

	/**
	 * @param subscriptionId
	 */
	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	/**
	 * @return the subscriptionId
	 */
	public String getSubscriptionId() {
		return subscriptionId;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	
}

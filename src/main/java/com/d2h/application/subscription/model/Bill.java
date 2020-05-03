/**
 * 
 */
package com.d2h.application.subscription.model;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author pravt
 *
 */
//builder pattern
public class Bill extends SubscriptionBill {

	protected String packageName;
	protected int discountApplied;
	protected int monthlyPrice;
	protected int subscriptionAmount;
	protected int noOfMonthsFor;
	protected int finalPriceAfterDiscount;
	protected int accountBalance;
	protected Set<Channel> channelList = null;
	protected Set<Services> serviceList = null;
	protected LocalDateTime buyAt;
	protected LocalDateTime validTill;
	

	private Bill(Builder builder) {
		super(builder.packageName);
		this.discountApplied = builder.discountApplied;
		this.monthlyPrice = builder.monthlyPrice;
		this.subscriptionAmount = builder.subscriptionAmount;
		this.noOfMonthsFor = builder.noOfMonthsFor;
		this.finalPriceAfterDiscount = builder.finalPriceAfterDiscount;
		this.accountBalance = builder.accountBalance;
		this.packageName = builder.packageName;
		this.buyAt = builder.buyAt;
		this.validTill = builder.validTill;
		this.channelList = builder.channelList;
		this.serviceList = builder.serviceList;
	}

	public static class Builder {

		public Set<Channel> channelList;
		private String packageName;
		private int discountApplied;
		private int monthlyPrice;
		private int subscriptionAmount;
		private int noOfMonthsFor;
		private int finalPriceAfterDiscount;
		private int accountBalance;
		private LocalDateTime buyAt;
		private LocalDateTime validTill;
		private Set<Services> serviceList;

		/**
		 * @param discountApplied the discountApplied to set
		 */
		public Builder discountApplied(int discountApplied) {
			this.discountApplied = discountApplied;
			return this;
		}

		/**
		 * @param monthlyPrice the monthlyPrice to set
		 */
		public Builder monthlyPrice(int monthlyPrice) {
			this.monthlyPrice = monthlyPrice;
			return this;
		}

		/**
		 * @param subscriptionAmount the subscriptionAmount to set
		 */
		public Builder subscriptionAmount(int subscriptionAmount) {
			this.subscriptionAmount = subscriptionAmount;
			return this;
		}

		/**
		 * @param noOfMonthsFor the noOfMonthsFor to set
		 */
		public Builder noOfMonthsFor(int noOfMonthsFor) {
			this.noOfMonthsFor = noOfMonthsFor;
			return this;
		}

		/**
		 * @param finalPriceAfterDiscount the finalPriceAfterDiscount to set
		 */
		public Builder finalPriceAfterDiscount(int finalPriceAfterDiscount) {
			this.finalPriceAfterDiscount = finalPriceAfterDiscount;
			return this;
		}

		/**
		 * @param accountBalance the accountBalance to set
		 */
		public Builder accountBalance(int accountBalance) {
			this.accountBalance = accountBalance;
			return this;
		}

		/**
		 * @param packageName the packageName to set
		 */
		public Builder packageName(String packageName) {
			this.packageName = packageName;
			return this;
		}

		public Bill build() {
			return new Bill(this);
		}

		public Builder buyAt(LocalDateTime buyAt) {
			this.buyAt = buyAt;
			return this;
		}
		
		public Builder setChannelList(Set<Channel> channelList) {
			this.channelList = channelList;
			return this;
		}
		
		/**
		 * @param serviceList the serviceList to set
		 */
		public Builder setServiceList(Set<Services> serviceList) {
			this.serviceList = serviceList;
			return this;
		}


		public Builder validTill(LocalDateTime validTill) {
			this.validTill = validTill;
			return this;
		}
	}

	/**
	 * @return the discountApplied
	 */
	public int getDiscountApplied() {
		return discountApplied;
	}

	/**
	 * @return the monthlyPrice
	 */
	public int getMonthlyPrice() {
		return monthlyPrice;
	}

	/**
	 * @return the subscriptionAmount
	 */
	public int getSubscriptionAmount() {
		return subscriptionAmount;
	}

	/**
	 * @return the noOfMonthsFor
	 */
	public int getNoOfMonthsFor() {
		return noOfMonthsFor;
	}

	/**
	 * @return the finalPriceAfterDiscount
	 */
	public int getFinalPriceAfterDiscount() {
		return finalPriceAfterDiscount;
	}

	/**
	 * @return the accountBalance
	 */
	public int getAccountBalance() {
		return accountBalance;
	}

	/**
	 * @param discountApplied the discountApplied to set
	 */
	public void setDiscountApplied(int discountApplied) {
		this.discountApplied = discountApplied;
	}

	/**
	 * @param monthlyPrice the monthlyPrice to set
	 */
	public void setMonthlyPrice(int monthlyPrice) {
		this.monthlyPrice = monthlyPrice;
	}

	/**
	 * @param subscriptionAmount the subscriptionAmount to set
	 */
	public void setSubscriptionAmount(int subscriptionAmount) {
		this.subscriptionAmount = subscriptionAmount;
	}

	/**
	 * @param noOfMonthsFor the noOfMonthsFor to set
	 */
	public void setNoOfMonthsFor(int noOfMonthsFor) {
		this.noOfMonthsFor = noOfMonthsFor;
	}

	/**
	 * @param finalPriceAfterDiscount the finalPriceAfterDiscount to set
	 */
	public void setFinalPriceAfterDiscount(int finalPriceAfterDiscount) {
		this.finalPriceAfterDiscount = finalPriceAfterDiscount;
	}

	/**
	 * @param accountBalance the accountBalance to set
	 */
	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}

	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @param packageName the packageName to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	
	public LocalDateTime getBuyAt() {
		return buyAt;
	}
	
	

	@Override
	public String toString() {
		return String.format(
				"SubscriptionBill\n> packageName = %s\n> channelList=%s\n> serviceList=%s\n> discountApplied = %s\n> monthlyPrice = %s\n> subscriptionAmount = %s\n> noOfMonthsFor = %s\n> finalPriceAfterDiscount = %s\n> accountBalance = %s\n> buyAt = %s\n> validTill = %s",
				packageName, channelList, serviceList, discountApplied, monthlyPrice, subscriptionAmount, noOfMonthsFor, finalPriceAfterDiscount,
				accountBalance, buyAt, validTill);
	}

//	@Override
//	public String toString() {
//		return String.format("PackageBill [channelList=%s]", channelList);
//	}

	public void setBuyAt(LocalDateTime buyAt) {
		this.buyAt = buyAt;
	}

	public LocalDateTime getValidTill() {
		return validTill;
	}

	public void setValidTill(LocalDateTime validTill) {
		this.validTill = validTill;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buyAt == null) ? 0 : buyAt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bill other = (Bill) obj;
		if (buyAt == null) {
			if (other.buyAt != null)
				return false;
		} else if (!buyAt.equals(other.buyAt))
			return false;
		return true;
	}

	@Override
	public int showPrice() {
		return finalPriceAfterDiscount;
	}

	/**
	 * @return the channelList
	 */
	public Set<Channel> getChannelList() {
		return channelList;
	}

	/**
	 * @return the serviceList
	 */
	public Set<Services> getServiceList() {
		return serviceList;
	}

	
	
}

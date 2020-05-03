/**
 * 
 */
package com.d2h.application.subscription.model;

/**
 * @author pravin sarode
 *
 */
public class Services {

	private String name;
	private int baseCharges;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBaseCharges() {
		return baseCharges;
	}

	public void setBaseCharges(int baseCharges) {
		this.baseCharges = baseCharges;
	}

	/**
	 * @param name
	 * @param baseCharges
	 */
	public Services(String name, int baseCharges) {
		super();
		this.name = name;
		this.baseCharges = baseCharges;
	}

	@Override
	public String toString() {
		return String.format("Services [name=%s, baseCharges=%s]", name, baseCharges);
	}

	
}


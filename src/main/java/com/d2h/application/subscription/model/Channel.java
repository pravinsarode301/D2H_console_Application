/**
 * 
 */
package com.d2h.application.subscription.model;

import java.util.Objects;

/**
 * @author pravt
 *
 */
public class Channel {

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

	public Channel(String name, int baseCharges) {
		super();
		this.name = name;
		this.baseCharges = baseCharges;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Channel)) {
			return false;
		}
		Channel other = (Channel) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return String.format("Channel [name=%s, baseCharges=%s]", name, baseCharges);
	}
	
	
	

}

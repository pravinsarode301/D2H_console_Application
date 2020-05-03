/**
 * 
 */
package com.d2h.application.interfaces;

import com.d2h.application.databases.SubscriptionMasterDB;

/**
 * @author pravt
 *
 */
public abstract class SatTV {
	int count = 1;

	public void setup() {
		System.out.println("satalite is setting up ....................");
	}

	public void intialise() {
		System.out.println("satalite is inilizing .....................");
	}

	void loadAssets() {
		System.out.println("Loading satalite Assets! ..................");
	}

	public void printMenu() {
		System.out.println("Our Menu are as follow !");
		SubscriptionMasterDB.menu.stream().forEach(x -> {
			System.out.println(count + ". " + x);
			count = count + 1;

		});
		count = 1;
	}

	// Method template pattern
	public final void start() {
		loadAssets();
		setup();
		intialise();
		System.out.println("Welcome to SatTV .");
	}

}

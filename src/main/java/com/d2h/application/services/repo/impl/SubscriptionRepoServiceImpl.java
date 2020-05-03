/**
 * 
 */
package com.d2h.application.services.repo.impl;

import com.d2h.application.databases.SubscriptionMasterDB;
import com.d2h.application.services.repo.SubscriptionRepoService;

/**
 * @author pravt
 *
 */
public class SubscriptionRepoServiceImpl implements SubscriptionRepoService {

	@Override
	public void getServicesDeatils() {
		SubscriptionMasterDB.submap.keySet().stream().forEach(x -> {

			System.out.println("Available packs for subscription :" + x.name() + "\n");

			System.out.println("Available channels for subscription\n");
			SubscriptionMasterDB.submap.get(x).stream().forEach(y -> {

				System.out.println("name:" + y.name() + " price:" + y.price);

			});

		});
		System.out.println("Available services for subscription ");
		SubscriptionMasterDB.SpecialServicePack.stream().forEach(y -> {
			System.out.println("name:" + y.name() + " Service price:" + y.price);

		});

	}

}

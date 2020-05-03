/**
 * 
 */
package com.d2h.application.databases;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author pravt
 *
 */
public class SubscriptionMasterDB {
	public static final List<Channels> gold_pack = new CopyOnWriteArrayList<>();
	public static final Map<packs, List<Channels>> submap = new ConcurrentHashMap<>();
	public static final List<Channels> silver_pack = new CopyOnWriteArrayList<>();
	public static final List<SpecialService> SpecialServicePack = new CopyOnWriteArrayList<>();

	public enum packs {
		GOLD, SILVER;
	}

	public enum Channels {
		DD_National(10), DD_Sport(10), DD_Sahyandri(10), Zee(20), Sony(20), Star_plus(30), Discovery(50), NatGeo(100);

		public int price;

		private Channels(int price) {
			this.price = price;
		}

	}
	
	public enum SpecialService {
		LearnEnglish(200), LearnCooking(100);

		public int price;

		private SpecialService(int price) {
			this.price = price;
		}

	}

	static {
		gold_pack.add(Channels.Zee);
		gold_pack.add(Channels.Sony);
		gold_pack.add(Channels.Star_plus);
		gold_pack.add(Channels.Discovery);
		gold_pack.add(Channels.NatGeo);
		silver_pack.add(Channels.Zee);
		silver_pack.add(Channels.Sony);
		silver_pack.add(Channels.Star_plus);
		SpecialServicePack.add(SpecialService.LearnCooking);
		SpecialServicePack.add(SpecialService.LearnEnglish);
	}

	static {
		submap.put(packs.GOLD, gold_pack);
		submap.put(packs.SILVER, silver_pack);
	}

	
	private static String[] menuAry = { "View current balance in the account", "Recharge Account",
			"View available packs, channels and services", "Subscribe to base packs",
			"Add channels to an existing subscription", "Subscribe to special services",
			"View current subscription details", "Update email and phone number for notifications","Subsciption Hisotry", "My Profile", "Logout", "Exit" };
	
	public static final List<String> menu = new CopyOnWriteArrayList<>(menuAry);
}

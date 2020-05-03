/**
 * 
 */
package com.d2h.application.interfaces.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.activity.InvalidActivityException;

import com.d2h.application.databases.SubscriptionMasterDB;
import com.d2h.application.databases.SubscriptionWriteDB;
import com.d2h.application.databases.UserWriteDB;
import com.d2h.application.interfaces.Reception;
import com.d2h.application.interfaces.SatTV;
import com.d2h.application.login.services.thread.LoginThread;
import com.d2h.application.services.repo.SubscriptionRepoService;
import com.d2h.application.services.repo.impl.SubscriptionRepoServiceImpl;
import com.d2h.application.subscription.decorator.Subscription;
import com.d2h.application.subscription.model.Channel;
import com.d2h.application.subscription.model.Services;
import com.d2h.application.subscription.service.cqrs.impl.SubscriptionCommandServiceImpl;
import com.d2h.application.subscription.service.cqrs.impl.SubscriptionQueryServiceImpl;
import com.d2h.application.subscription.services.cqrs.SubscriptionCommandService;
import com.d2h.application.subscription.services.cqrs.SubscriptionQueryService;
import com.d2h.application.user.model.User;
import com.d2h.application.user.services.cqrs.UserCommandService;
import com.d2h.application.user.services.cqrs.UserQueryService;
import com.d2h.application.user.services.cqrs.impl.UserCommandServiceImpl;
import com.d2h.application.user.services.cqrs.impl.UserQueryServiceImpl;

/**
 * @author pravin sarode
 *
 */
public class ReceptionImpl implements Reception {

	private UserQueryService userQueryService = new UserQueryServiceImpl();
	private UserCommandService userCommandService = null;
	public User user = null;
	public static boolean isValidUser = false;
	public static SatTV tv = new SatTvImpl();
	public final static Scanner input = new Scanner(System.in);
	short selection = 0;
	// default setup
	private SubscriptionRepoService subscriptionRepoService = null;
	private Subscription subscription = null;
	private SubscriptionCommandService subscriptionCommandService = null;
	private SubscriptionQueryService subscriptionQueryService = null;
	
	/**
	 * 
	 */
	public ReceptionImpl() {
		//Mandate setup
		 UserWriteDB dbService = UserWriteDB.getInstance();
		 SubscriptionWriteDB writeDbService = SubscriptionWriteDB.getInstance();
	}

	@Override
	public void login() throws Exception {
		// Login module

		System.out.println("Login First, Use below master login \nusername: admin1\npassword: admin1");

		LoginThread thread = new LoginThread();
		boolean status = true;
		while (status) {
			System.out.println("Enter userName");
			String username = input.next();
			System.out.println("Enter password");
			String password = input.next();
			thread.setUsername(username);
			thread.setPassword(password);

			if (thread.call()) {
				user = userQueryService.getUserDetails(username, password);
				status = false;
				isValidUser = true;
				break;
			}
			status = true;
		}

		doAssist();
	}

	@Override
	public void startTV() {
		tv.start();

	}

	@Override
	public void showCurrentlBalace() throws InvalidActivityException {

		System.out.println("Current balance is " + userQueryService.checkBalance() + " Rs");
	}

	@Override
	public void doAssist() throws Exception {

		// default setup
		subscriptionRepoService = new SubscriptionRepoServiceImpl();
		subscriptionCommandService = new SubscriptionCommandServiceImpl();
		subscriptionQueryService = new SubscriptionQueryServiceImpl();
		userCommandService = new UserCommandServiceImpl(user);
		userQueryService.setUserEntityForService(user);
		if (isValidUser) {
			// if (true) {

			tv.printMenu();
			System.out.println("Make proper selection to proceed");
			try {
				while (input.hasNextShort()) {
					selection = input.nextShort();
					switch (selection) {
					case 1:
						try {
							showCurrentlBalace();
						} catch (Exception e1) {
							break;
						}
						break;
					case 2:
						try {
							rechargeAccount();
						} catch (Exception e1) {
							break;
						}
						break;
					case 3:
						try {
							showAvailablePacks();
						} catch (Exception e1) {
							break;
						}
						break;
					case 4:
						try {
							doSubscribePack();
						} catch (Exception e1) {
							break;
						}
						break;
					case 5:
						try {
							addChannelsToSubscription();
						} catch (Exception e) {
							break;
						}
						break;
					case 6:
						try {
							addSpecialServicesToSubscription();
						} catch (Exception e1) {
							break;
						}
						break;
					case 7:
						try {
							viewCurrentSubscriptionDetails();
						} catch (Exception e1) {
							break;
						}
						break;
					case 8:
						try {
							updateUserDeatils();
						} catch (Exception e1) {
							break;
						}
						break;
					case 9:
						try {
							viewSubscriptionHistory();
						} catch (Exception e1) {
							break;
						}
						break;
					case 10:
						showMyProfile();
						break;
					case 11:
						logout();
						break;
					case 12:
						System.exit(0);
					default:
						System.out.println("invalid choice");
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 */
	@Override
	public void showMyProfile() {
		System.out.println(user.toString());

	}

	@Override
	public void rechargeAccount() throws InvalidActivityException {
		System.out.println("Enter amount to recharge ");
		userCommandService.rechargeAccount(input.nextInt());
	}

	@Override
	public void showAvailablePacks() {
		System.out.println("Available packs for subscription ");
		subscriptionRepoService.getServicesDeatils();
	}

	@Override
	public void doSubscribePack() throws Exception {
		if (subscription != null) {
			// System.out.println("you already subscribe to subscription, try adding new
			// chanels");
			throw new InvalidActivityException("you already subscribe to subscription, try adding new chanels");
		}
		System.out.println("Enter the Pack you wish to subscribe: (Silver: ‘S’, Gold: ‘G’)");
		String choice = input.next();
		System.out.println("Enter the months: ");
		int duration = input.nextInt();
		subscription = subscriptionCommandService.subscribe(choice, duration, user);

		if (subscription != null) {
			user.setCurrentSubscription(subscription);
		}
	}

	@Override
	public void addChannelsToSubscription() throws InvalidActivityException {
		if (user.getCurrentSubscription() == null) {
			System.out.println(
					"Currently you have not subscribe to any channel, Subscribe the package first by selecting option 4");
			throw new InvalidActivityException(
					"Currently you have not subscribe to any channel, Subscribe the package first by selecting option 4");
		}

		System.out.println("Add channels to existing subscription ");
		System.out.println("Enter channel names to add (separated by commas):");
		String channelString = input.next();
		List<Channel> list = null;

		if (!channelString.isEmpty()) {
			try {
				list = Arrays.asList(channelString.split(",")).stream().map(x -> {
					return new Channel(SubscriptionMasterDB.Channels.valueOf(x.trim()).name(),
							SubscriptionMasterDB.Channels.valueOf(x.trim()).price);
				}).collect(Collectors.toList());
				subscriptionCommandService.addChannelsToSubscription(list, user);
			} catch (Exception e) {
				// e.printStackTrace();
				System.out.println(e.getLocalizedMessage());
				throw new InvalidActivityException(e.getLocalizedMessage());
			}
		}

	}

	@Override
	public void addSpecialServicesToSubscription() throws InvalidActivityException {
		System.out.println("Subscribe to special services ");
		System.out.println("Enter the service name: ");
		SubscriptionMasterDB.SpecialServicePack.stream().forEach(y -> {
			System.out.println("name:" + y.name() + " Service price:" + y.price);

		});
		String service = input.next();

		if (!service.isEmpty()) {
			try {
				List<Services> ServicesList = Arrays.asList(service.split(",")).stream().map(x -> {
					return new Services(SubscriptionMasterDB.SpecialService.valueOf(x.trim()).name(),
							SubscriptionMasterDB.SpecialService.valueOf(x.trim()).price);
				}).collect(Collectors.toList());

				subscriptionCommandService.addSpecialServicesToSubscription(ServicesList, user);
			} catch (Exception e) {
				// e.printStackTrace();
				System.out.println(e.getLocalizedMessage());
				throw new InvalidActivityException(e.getLocalizedMessage());
			}
		}

	}

	@Override
	public void viewCurrentSubscriptionDetails() throws Exception {
		System.out.println("View current subscription details ");
		// user.getCurrentSubscription().getinfo();
		subscriptionQueryService.viewCurrentSubscriptionDetails(user.getSubscriptionId());

	}

	@Override
	public void updateUserDeatils() throws Exception {
		System.out.println("Update email and phone number for notifications ");
		System.out.println("Enter the email");
		String email = input.next();
		user.setAddress(email);
		System.out.println("Enter phone");
		String phonenumber = input.next();
		user.setPhoneNumber(phonenumber);
		user = userCommandService.updateUserdetails(user);
	}

	@Override
	public void viewSubscriptionHistory() throws Exception {
		// subscription history

		if (user.getLogs().isEmpty()) {
			System.out.println("no details found.!");
		}

		subscriptionQueryService.viewSubscriptionHistory(user.getSubscriptionId());
	}

	@Override
	public void logout() throws Exception {
		isValidUser = false;
		user = null;
		userCommandService = null;
		subscription = null;
		System.out.println("successfully logout");
		login();
		// System.exit(0);
	}

}

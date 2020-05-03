/**
 * 
 */
package com.d2h.application.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.powermock.modules.junit4.PowerMockRunner;

import com.d2h.application.subscription.decorator.Subscription;
import com.d2h.application.subscription.model.Bill;
import com.d2h.application.subscription.model.Channel;
import com.d2h.application.subscription.model.Services;
import com.d2h.application.subscription.service.cqrs.impl.SubscriptionCommandServiceImpl;
import com.d2h.application.subscription.services.cqrs.SubscriptionCommandService;
import com.d2h.application.user.model.User;

/**
 * @author pravin sarode
 *
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(PowerMockRunner.class)
public class SubscriptionServiceImplTest {
	private SubscriptionCommandService subscriptionCommandService = new SubscriptionCommandServiceImpl();
	
	private User user = new User.Builder().setUserName("admin1").setPassword("admin1").build();

	@Rule
	ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void test001_subscriptionWithNullValue() throws Exception {
		expectedEx.expect(NullPointerException.class);
		subscriptionCommandService.subscribe(null, 0, null);
	}

	@Test
	public void test002_subscriptionWithUserLowBalance() throws Exception {
		expectedEx.expect(Exception.class);
		expectedEx.expectMessage("account balance is low, refill the same in order to subcribe the plan");
		user.setBalance(0);
		subscriptionCommandService.subscribe("G", 4, user);
	}

	@Test // (expected = Exception.class)
	public void test003_subscriptionWithInvalidChoise() throws Exception {
		expectedEx.expect(Exception.class);
		expectedEx.expectMessage("invalid choise, please make proper choise to continue");
		subscriptionCommandService.subscribe("C", 4, user);
	}

	@Test
	public void test004_subscriptionWithValidChoise() throws Exception {

		Subscription sub = subscriptionCommandService.subscribe("G", 4, user);
		assertNotNull(sub);

		// 10% discount should be added for 4 month i.e 40 total amount calculated 400
		// and after discount it should be 360
		assertEquals(sub.getTotalFair(), 360);
		assertEquals(sub.subscriptionName, "GOLD");
		assertEquals(sub.getMonthlyCharges(), new Integer(100));

	}

	@Test
	public void test005_verifySubscriptionBill() throws Exception {

		Subscription sub = subscriptionCommandService.subscribe("G", 4, user);
		assertNotNull(sub);
		// 10% discount should be added for 4 month i.e 40 total amount calculated 400
		// and after discount it should be 360
		assertNotNull(sub.getBills());
		Bill bill = (Bill) sub.getBills().getBills().peek();
		assertEquals(bill.getSubscriptionAmount(), 400);
		assertEquals(bill.getMonthlyPrice(), 100);
		assertEquals(bill.getAccountBalance(), 360);
		assertEquals(bill.getDiscountApplied(), 40);
		assertEquals(bill.getFinalPriceAfterDiscount(), 360);
		assertEquals(bill.getNoOfMonthsFor(), 4);
	}

	@Test
	public void test006_addChannelsToExistingSub() throws Exception {

		Subscription sub = subscriptionCommandService.subscribe("G", 4, user);
		assertNotNull(sub);

		List<Channel> list = new ArrayList<Channel>();
		list.add(new Channel("Zee", 10));
		list.add(new Channel("Zee1", 10));

		sub = subscriptionCommandService.addChannelsToSubscription(list, user);
		assertEquals(sub.getVarChannelsList().size(), 2);

		assertNotNull(sub.getBills());
		Bill bill = (Bill) sub.getBills().getBills().peek();
		//assertEquals(bill.getMonthlyPrice(), 10);
		assertEquals(bill.getAccountBalance(), 380);
		assertEquals(bill.getDiscountApplied(), 0);
		//assertEquals(bill.getFinalPriceAfterDiscount(), 10);
		assertEquals(bill.getNoOfMonthsFor(), 1);
	}

	@Test
	public void test007_addServicesChannelsToExistingSub() throws Exception {

		Subscription sub = subscriptionCommandService.subscribe("G", 4, user);
		assertNotNull(sub);

		List<Services> list = new ArrayList<Services>();
		list.add(new Services("Learn English", 100));

		sub = subscriptionCommandService.addSpecialServicesToSubscription(list, user);
		assertEquals(sub.getServicesList().size(), 1);

		assertNotNull(sub.getBills());
		Bill bill = (Bill) sub.getBills().getBills().peek();
		assertEquals(bill.getMonthlyPrice(), 100);
		assertEquals(bill.getAccountBalance(), 460);
		assertEquals(bill.getDiscountApplied(), 0);
		assertEquals(bill.getFinalPriceAfterDiscount(), 100);
		assertEquals(bill.getNoOfMonthsFor(), 1);

	}

}

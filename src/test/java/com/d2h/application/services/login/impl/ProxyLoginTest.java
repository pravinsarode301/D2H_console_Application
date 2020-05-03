/**
 * 
 */
package com.d2h.application.services.login.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;
import java.util.function.BiPredicate;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.powermock.modules.junit4.PowerMockRunner;

import com.d2h.application.databases.UserWriteDB;
import com.d2h.application.login.services.LoginService;
import com.d2h.application.login.services.impl.ProxyLogin;

/**
 * @author pravin sarode
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(PowerMockRunner.class)
public class ProxyLoginTest {

	
	private LoginService loginService = new ProxyLogin();

	@BeforeClass
	public static void setup() {
		 UserWriteDB writeDb = UserWriteDB.getInstance();	
	}
	
	// valid combination
	@Test
	public void test001_loginWithValidCombination() {
		BiPredicate<Optional<String>, Optional<String>> bipredecate = loginService.doLogin();
		assertTrue(bipredecate.test(Optional.of("admin1"), Optional.of("admin1")));
		assertTrue(bipredecate.test(Optional.of("admin2"), Optional.of("admin2")));
		assertTrue(bipredecate.test(Optional.of("admin3"), Optional.of("admin3")));

	}

	// invalid combination
	@Test
	public void test002_loginWithInValidCombination() {
		BiPredicate<Optional<String>, Optional<String>> bipredecate = loginService.doLogin();
		assertFalse(bipredecate.test(Optional.of("admin2"), Optional.of("admin1")));
		assertFalse(bipredecate.test(Optional.of("admin3"), Optional.of("admin2")));
		assertFalse(bipredecate.test(Optional.of("admin4"), Optional.of("admin3")));

	}

	// invalid combination of username and password
	@Test
	public void test003_loginWithInNullCombination() {
		BiPredicate<Optional<String>, Optional<String>> bipredecate = loginService.doLogin();
		assertFalse(bipredecate.test(Optional.of(""), Optional.of("admin1")));
		assertFalse(bipredecate.test(Optional.of("admin3"), Optional.of("")));
		assertFalse(bipredecate.test(Optional.of(""), Optional.of("")));
	}
}

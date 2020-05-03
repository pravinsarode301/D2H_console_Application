/**
 * 
 */
package com.d2h.application;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.d2h.application.services.impl.SubscriptionServiceImplTest;
import com.d2h.application.services.login.impl.ProxyLoginTest;

/**
 * @author pravin sarode
 *
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({
	ProxyLoginTest.class,
	SubscriptionServiceImplTest.class
})
public class TestBootstrap {

}

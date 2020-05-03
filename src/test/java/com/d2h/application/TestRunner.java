/**
 * 
 */
package com.d2h.application;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * @author pravin sarode
 *
 */
public class TestRunner {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(TestBootstrap.class);

		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		System.out.println(result.wasSuccessful());
	}

}

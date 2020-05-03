/**
 * 
 */
package com.d2h.application.subscription.handler;

import com.d2h.application.subscription.decorator.Subscription;

/**
 * @author pravin sarode
 *
 */

//chain of responsibility pattern
public abstract class RequestHandler {
	
	public abstract RequestHandler nextHandler();

	public abstract Subscription processRequest(String choice) throws Exception;
	
}

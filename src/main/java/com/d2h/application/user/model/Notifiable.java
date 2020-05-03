/**
 * 
 */
package com.d2h.application.user.model;

/**
 * @author pravt
 *
 */
public interface Notifiable {

	default public void notify(String messagee) {
		System.out.println(messagee);
	}
}

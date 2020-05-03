/**
 * 
 */
package com.d2h.application;

import com.d2h.application.interfaces.Reception;
import com.d2h.application.interfaces.impl.ReceptionImpl;

/**
 * @author pravin sarode (prvnsarode@gmail.com)
 *
 */
public class MainApp {

	public static void main(String[] args) {

		Reception reception = new ReceptionImpl();
		// start tv
		reception.startTV();

		// login module
		try {
			reception.login();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
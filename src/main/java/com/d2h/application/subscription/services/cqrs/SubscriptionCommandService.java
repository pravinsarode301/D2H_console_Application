/**
 * 
 */
package com.d2h.application.subscription.services.cqrs;

import java.util.Collection;
import java.util.List;

import com.d2h.application.subscription.decorator.Subscription;
import com.d2h.application.subscription.model.Channel;
import com.d2h.application.subscription.model.Services;
import com.d2h.application.user.model.User;

/**
 * @author pravin sarode
 *
 */
public interface SubscriptionCommandService {

	Subscription subscribe(String choice, Integer duration, User user) throws Exception;

	Subscription addChannelsToSubscription(Collection<Channel> channels, User user) throws Exception;

	Subscription addSpecialServicesToSubscription(List<Services> servicesList, User user) throws Exception;
}

package paypal.api.payments;

import paypal.base.rest.*;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.Getter; import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class EventType  extends PayPalResource {

	/**
	 * Unique event-type name.
	 */
	private String name;

	/**
	 * Human readable description of the event-type
	 */
	private String description;
	
	/**
	 * Default Constructor
	 */
	public EventType() {
	}

	/**
	 * Parameterized Constructor
	 */
	public EventType(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the list of events-types subscribed by the given Webhook.
	 * @deprecated Please use {@link #subscribedEventTypes(APIContext, String)} instead.
	 * @param accessToken
	 *            Access Token used for the API call.
	 * @param webhookId
	 *            String
	 * @return EventTypeList
	 * @throws PayPalRESTException
	 */
	public static EventTypeList subscribedEventTypes(String accessToken, String webhookId) throws PayPalRESTException {
		APIContext apiContext = new APIContext(accessToken);
		return subscribedEventTypes(apiContext, webhookId);
	}

	/**
	 * Retrieves the list of events-types subscribed by the given Webhook.
	 * @param apiContext
	 *            {@link APIContext} used for the API call.
	 * @param webhookId
	 *            String
	 * @return EventTypeList
	 * @throws PayPalRESTException
	 */
	public static EventTypeList subscribedEventTypes(APIContext apiContext, String webhookId) throws PayPalRESTException {

		if (webhookId == null) {
			throw new IllegalArgumentException("webhookId cannot be null");
		}
		Object[] parameters = new Object[] {webhookId};
		String pattern = "v1/notifications/webhooks/{0}/event-types";
		String resourcePath = RESTUtil.formatURIPath(pattern, parameters);
		String payLoad = "";
		EventTypeList eventTypeList =  configureAndExecute(apiContext, HttpMethod.GET, resourcePath, payLoad, EventTypeList.class);
		if (eventTypeList == null) {
			eventTypeList = new EventTypeList();
		}
		
		return eventTypeList;
	}


	/**
	 * Retrieves the master list of available Webhooks events-types resources for any webhook to subscribe to.
	 * @deprecated Please use {@link #availableEventTypes(APIContext)} instead.
	 * @param accessToken
	 *            Access Token used for the API call.
	 * @return EventTypeList
	 * @throws PayPalRESTException
	 */
	public static EventTypeList availableEventTypes(String accessToken) throws PayPalRESTException {
		APIContext apiContext = new APIContext(accessToken);
		return availableEventTypes(apiContext);
	}

	/**
	 * Retrieves the master list of available Webhooks events-types resources for any webhook to subscribe to.
	 * @param apiContext
	 *            {@link APIContext} used for the API call.
	 * @return EventTypeList
	 * @throws PayPalRESTException
	 */
	public static EventTypeList availableEventTypes(APIContext apiContext) throws PayPalRESTException {

		String resourcePath = "v1/notifications/webhooks-event-types";
		String payLoad = "";
		EventTypeList eventTypeList = configureAndExecute(apiContext, HttpMethod.GET, resourcePath, payLoad, EventTypeList.class);
		return eventTypeList;
	}


}

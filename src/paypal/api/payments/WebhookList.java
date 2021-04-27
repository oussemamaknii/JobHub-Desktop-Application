package paypal.api.payments;

import paypal.base.rest.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class WebhookList  extends PayPalResource {

	/**
	 * A list of webhooks.
	 */
	private List<Webhook> webhooks;

	/**
	 * Default Constructor
	 */
	public WebhookList() {
	}

	/**
	 * Retrieves all Webhooks for the application associated with access token.
	 * @deprecated Please use {@link #getAll(APIContext)} instead.
	 *
	 * @param accessToken
	 *            Access Token used for the API call.
	 * @return WebhookList
	 * @throws PayPalRESTException
	 */
	public WebhookList getAll(String accessToken) throws PayPalRESTException {
		APIContext apiContext = new APIContext(accessToken);
		return getAll(apiContext);
	}

	/**
	 * Retrieves all Webhooks for the application associated with access token.
	 * @param apiContext
	 *            {@link APIContext} used for the API call.
	 * @return WebhookList
	 * @throws PayPalRESTException
	 */
	public WebhookList getAll(APIContext apiContext) throws PayPalRESTException {
		Object[] parameters = new Object[] {};
		String pattern = "v1/notifications/webhooks/";
		String resourcePath = RESTUtil.formatURIPath(pattern, parameters);
		String payLoad = "";
		return configureAndExecute(apiContext, HttpMethod.GET, resourcePath, payLoad, WebhookList.class);
	}
}

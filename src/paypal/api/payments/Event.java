package paypal.api.payments;

import paypal.base.Constants;
import paypal.base.SDKUtil;
import paypal.base.SSLUtil;
import paypal.base.rest.*;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.Getter; import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Event  extends PayPalResource {
	
	private static final Logger log = LoggerFactory.getLogger(Event.class);

	/**
	 * Identifier of the Webhooks event resource.
	 */
	private String id;

	/**
	 * Time the resource was created.
	 */
	private String createTime;

	/**
	 * Name of the resource contained in resource element.
	 */
	private String resourceType;

	/**
	 * Name of the event type that occurred on resource, identified by data_resource element, to trigger the Webhooks event.
	 */
	private String eventType;

	/**
	 * A summary description of the event. E.g. A successful payment authorization was created for $$
	 */
	private String summary;

	/**
	 * This contains the resource that is identified by resource_type element.
	 */
	private Object resource;

	/**
	 * Hateoas links.
	 */
	private List<Links> links;

	public static Logger getLog() {
		return log;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Object getResource() {
		return resource;
	}

	public void setResource(Object resource) {
		this.resource = resource;
	}

	public List<Links> getLinks() {
		return links;
	}

	public void setLinks(List<Links> links) {
		this.links = links;
	}

	/**
	 * Default Constructor
	 */
	public Event() {
	}

	/**
	 * Retrieves the Webhooks event resource identified by event_id. Can be used to retrieve the payload for an event.
	 * @deprecated Please use {@link #get(APIContext, String)} instead.
	 * @param accessToken
	 *            Access Token used for the API call.
	 * @param eventId
	 *            String
	 * @return Event
	 * @throws PayPalRESTException
	 */
	@Deprecated
	public static Event get(String accessToken, String eventId) throws PayPalRESTException {
		APIContext apiContext = new APIContext(accessToken);
		return get(apiContext, eventId);
	}

	/**
	 * Retrieves the Webhooks event resource identified by event_id. Can be used to retrieve the payload for an event.
	 * @param apiContext
	 *            {@link APIContext} used for the API call.
	 * @param eventId
	 *            String
	 * @return Event
	 * @throws PayPalRESTException
	 */
	public static Event get(APIContext apiContext, String eventId) throws PayPalRESTException {

		if (eventId == null) {
			throw new IllegalArgumentException("eventId cannot be null");
		}
		Object[] parameters = new Object[] {eventId};
		String pattern = "v1/notifications/webhooks-events/{0}";
		String resourcePath = RESTUtil.formatURIPath(pattern, parameters);
		String payLoad = "";
		return configureAndExecute(apiContext, HttpMethod.GET, resourcePath, payLoad, Event.class);
	}


	/**
	 * Resends the Webhooks event resource identified by event_id.
	 * @deprecated Please use {@link #resend(APIContext)} instead.
	 * @param accessToken
	 *            Access Token used for the API call.
	 * @return Event
	 * @throws PayPalRESTException
	 */
	@Deprecated
	public Event resend(String accessToken) throws PayPalRESTException {
		APIContext apiContext = new APIContext(accessToken);
		return resend(apiContext);
	}

	/**
	 * Resends the Webhooks event resource identified by event_id.
	 * @param apiContext
	 *            {@link APIContext} used for the API call.
	 * @return Event
	 * @throws PayPalRESTException
	 */
	public Event resend(APIContext apiContext) throws PayPalRESTException {

		if (this.getId() == null) {
			throw new IllegalArgumentException("Id cannot be null");
		}
		Object[] parameters = new Object[] {this.getId()};
		String pattern = "v1/notifications/webhooks-events/{0}/resend";
		String resourcePath = RESTUtil.formatURIPath(pattern, parameters);
		String payLoad = "";
		return configureAndExecute(apiContext, HttpMethod.POST, resourcePath, payLoad, Event.class);
	}


	/**
	 * Retrieves the list of Webhooks events resources for the application associated with token. The developers can use it to see list of past webhooks events.
	 * @deprecated Please use {@link #list(APIContext, String)} instead.
	 * @param accessToken
	 *            Access Token used for the API call.
	 * @return EventList
	 * @throws PayPalRESTException
	 */
	@Deprecated
	public static EventList list(String accessToken, String queryParams) throws PayPalRESTException {
		APIContext apiContext = new APIContext(accessToken);
		return list(apiContext, queryParams);
	}

	/**
	 * Retrieves the list of Webhooks events resources for the application associated with token. The developers can use it to see list of past webhooks events.
	 * @param apiContext
	 *            {@link APIContext} used for the API call.
	 * @return EventList
	 * @throws PayPalRESTException
	 */
	public static EventList list(APIContext apiContext, String queryParams) throws PayPalRESTException {

		String resourcePath = "v1/notifications/webhooks-events" + queryParams;
		String payLoad = "";
		return configureAndExecute(apiContext, HttpMethod.GET, resourcePath, payLoad, EventList.class);
	}
	
	/**
	 * Validates received event received from PayPal to webhook endpoint set for particular webhook Id with PayPal trust source, to verify Data and Certificate integrity.
	 * It validates both certificate chain, as well as data integrity. 
	 * 
	 * @param apiContext APIContext object
	 * @param headers Map of Headers received in the event, from request
	 * @param requestBody Request body received in the provided webhook
	 * @return true if valid, false otherwise
	 * @throws PayPalRESTException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws SignatureException
	 */
	public static boolean validateReceivedEvent(APIContext apiContext, Map<String, String> headers, String requestBody) throws PayPalRESTException, InvalidKeyException, NoSuchAlgorithmException, SignatureException  {

		if (headers == null) {
			throw new PayPalRESTException("Headers cannot be null");
		}

		Map<String, String> cmap;
		Boolean isChainValid = false, isDataValid = false;
		Collection<X509Certificate> trustCerts, clientCerts;

		// Load the configurations from all possible sources
		cmap = getConfigurations(apiContext);

		// Fetch Certificate Locations
		String clientCertificateLocation = SDKUtil.validateAndGet(headers, Constants.PAYPAL_HEADER_CERT_URL);
		// Default to `DigiCertSHA2ExtendedValidationServerCA` if none provided
		if (cmap != null && !cmap.containsKey(Constants.PAYPAL_TRUST_CERT_URL)) {
			cmap.put(Constants.PAYPAL_TRUST_CERT_URL, Constants.PAYPAL_TRUST_DEFAULT_CERT);
		}
		String trustCertificateLocation = SDKUtil.validateAndGet(cmap, Constants.PAYPAL_TRUST_CERT_URL);

		// Load certificates
		clientCerts = SSLUtil.getCertificateFromStream(SSLUtil.downloadCertificateFromPath(clientCertificateLocation, cmap));
		trustCerts = SSLUtil.getCertificateFromStream(Event.class.getClassLoader().getResourceAsStream(trustCertificateLocation));

		// Check if Chain Valid
		isChainValid = SSLUtil.validateCertificateChain(clientCerts, trustCerts, SDKUtil.validateAndGet(cmap, Constants.PAYPAL_WEBHOOK_CERTIFICATE_AUTHTYPE));

		log.debug("Is Chain Valid: " + isChainValid);
		if (isChainValid) {
			// If Chain Valid, check for data signature valid
			// Lets check for data now
			String webhookId = SDKUtil.validateAndGet(cmap, Constants.PAYPAL_WEBHOOK_ID);
			String actualSignatureEncoded = SDKUtil.validateAndGet(headers, Constants.PAYPAL_HEADER_TRANSMISSION_SIG);
			String authAlgo = SDKUtil.validateAndGet(headers, Constants.PAYPAL_HEADER_AUTH_ALGO);
			String transmissionId = SDKUtil.validateAndGet(headers, Constants.PAYPAL_HEADER_TRANSMISSION_ID);
			String transmissionTime = SDKUtil.validateAndGet(headers, Constants.PAYPAL_HEADER_TRANSMISSION_TIME);
			String expectedSignature = String.format("%s|%s|%s|%s", transmissionId, transmissionTime, webhookId, SSLUtil.crc32(requestBody));

			// Validate Data
			isDataValid = SSLUtil.validateData(clientCerts, authAlgo, actualSignatureEncoded, expectedSignature, requestBody, webhookId);

			log.debug("Is Data Valid: " + isDataValid);
			// Return true if both data and chain valid
			return isDataValid;
		}

		return false;
	}

	/**
	 * Returns configurations by merging apiContext configurations in Map format
	 * 
	 * @param apiContext
	 * @return Map of configurations to be used for particular request
	 */
	private static Map<String, String> getConfigurations(APIContext apiContext) {
		Map<String, String> cmap;
		if (apiContext != null) {
			if (apiContext.getConfigurationMap() == null) {
				apiContext.setConfigurationMap(new HashMap<String, String>());
			}
			cmap = SDKUtil.combineDefaultMap(apiContext.getConfigurationMap());
			cmap = SDKUtil.combineMap(cmap, PayPalResource.getConfigurations());
		} else {
			cmap = SDKUtil.combineDefaultMap(PayPalResource.getConfigurations());
		}
		return cmap;
	}
}

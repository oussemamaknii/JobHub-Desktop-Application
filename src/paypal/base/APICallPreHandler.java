package paypal.base;

import paypal.base.exception.ClientActionRequiredException;
import paypal.base.exception.OAuthException;
import paypal.base.rest.OAuthTokenCredential;

import java.util.Map;

/**
 * <code>APICallPreHandler</code> defines a high level abstraction for call
 * specific operations. PayPal REST API is provided by {@link paypal.base.rest.RESTAPICallPreHandler}
 */
public interface APICallPreHandler {

	/**
	 * Returns headers for HTTP call
	 * 
	 * @return Map of headers with name and value
	 * @throws OAuthException
	 */
	Map<String, String> getHeaderMap() throws OAuthException;

	/**
	 * Returns the payload for the API call. The implementation should take care
	 * in formatting the payload appropriately
	 * 
	 * @return Payload as String
	 */
	String getPayLoad();

	/**
	 * Returns the endpoint for the API call. The implementation may calculate
	 * the endpoint depending on parameters set on it. If no endpoint is found
	 * in the passed configuration, then SANDBOX endpoints (hardcoded in
	 * {@link Constants})are taken to be default for the API call.
	 * 
	 * @return Endpoint String.
	 */
	String getEndPoint();

	/**
	 * Returns {@link OAuthTokenCredential} configured for the api call
	 * 
	 * @return ICredential object
	 */
	OAuthTokenCredential getCredential();

	/**
	 * Validates settings and integrity before call
	 * 
	 * @throws ClientActionRequiredException
	 */
	void validate() throws ClientActionRequiredException;
	

	
	/**
	 * Return configurationMap
	 * 
	 * @return configurationMap in this call pre-handler
	 */
	public Map<String, String> getConfigurationMap();

}

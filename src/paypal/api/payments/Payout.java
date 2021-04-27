package paypal.api.payments;

import paypal.base.rest.*;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.Getter; import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Payout extends PayPalResource {

	/**
	 * The original batch header as provided by the payment sender.
	 */
	private PayoutSenderBatchHeader senderBatchHeader;

	/**
	 * An array of payout items (that is, a set of individual payouts).
	 */
	private List<PayoutItem> items;
	
	/**
	 * 
	 */
	private List<Links> links;

	
	/**
	 * Default Constructor
	 */
	public Payout() {
	}

	/**
	 * Parameterized Constructor
	 */
	public Payout(PayoutSenderBatchHeader senderBatchHeader,
			List<PayoutItem> items) {
		this.senderBatchHeader = senderBatchHeader;
		this.items = items;
	}

	/**
	 * You can submit a payout with a synchronous API call, which immediately returns the results of a PayPal payment.
	 * @deprecated Please use {@link #createSynchronous(APIContext)} instead.
	 * 
	 * @param accessToken
	 *            Access Token used for the API call.
	 * @return PayoutBatch
	 * @throws PayPalRESTException
	 */
	public PayoutBatch createSynchronous(String accessToken) throws PayPalRESTException {
		APIContext apiContext = new APIContext(accessToken);
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("sync_mode", "true");
		return create(apiContext, parameters);
	}
	
	/**
	 *  You can submit a payout with a synchronous API call, which immediately returns the results of a PayPal payment.
	 * 
	 * @param apiContext
	 *            {@link APIContext} used for the API call.
	 * @return PayoutBatch
	 * @throws PayPalRESTException
	 */
	public PayoutBatch createSynchronous(APIContext apiContext) throws PayPalRESTException {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("sync_mode", "true");
		return create(apiContext, parameters);
	}

	/**
	 * Create a payout batch resource by passing a sender_batch_header and an
	 * items array to the request URI. The sender_batch_header contains payout
	 * parameters that describe the handling of a batch resource while the items
	 * array conatins payout items.
	 * @deprecated Please use {@link #create(APIContext, Map)} instead.
	 * 
	 * @param accessToken
	 *            Access Token used for the API call.
	 * @param parameters
	 *            Map<String, String>
	 * @return PayoutBatch
	 * @throws PayPalRESTException
	 */
	public PayoutBatch create(String accessToken, Map<String, String> parameters) throws PayPalRESTException {
		APIContext apiContext = new APIContext(accessToken);
		return create(apiContext, parameters);
	}

	/**
	 * Create a payout batch resource by passing a sender_batch_header and an
	 * items array to the request URI. The sender_batch_header contains payout
	 * parameters that describe the handling of a batch resource while the items
	 * array conatins payout items.
	 * 
	 * @param apiContext
	 *            {@link APIContext} used for the API call.
	 * @param parameters
	 *            Map<String, String>
	 * @return PayoutBatch
	 * @throws PayPalRESTException
	 */
	public PayoutBatch create(APIContext apiContext, Map<String, String> parameters) throws PayPalRESTException {
		if (parameters == null) {
			parameters = new HashMap<String, String>();
		}
		Object[] parametersObj = new Object[] {parameters};
		String pattern = "v1/payments/payouts?sync_mode={0}";
		String resourcePath = RESTUtil.formatURIPath(pattern, parametersObj);
		String payLoad = this.toJSON();
		return configureAndExecute(apiContext, HttpMethod.POST,
				resourcePath, payLoad, PayoutBatch.class);
	}

	/**
	 * Obtain the status of a specific batch resource by passing the payout
	 * batch ID to the request URI. You can issue this call multiple times to
	 * get the current status.
	 * @deprecated Please use {@link #get(APIContext, String)} instead.
	 * 
	 * @param accessToken
	 *            Access Token used for the API call.
	 * @param payoutBatchId
	 *            String
	 * @return PayoutBatch
	 * @throws PayPalRESTException
	 */
	public static PayoutBatch get(String accessToken, String payoutBatchId)
			throws PayPalRESTException {
		APIContext apiContext = new APIContext(accessToken);
		return get(apiContext, payoutBatchId);
	}

	/**
	 * Obtain the status of a specific batch resource by passing the payout
	 * batch ID to the request URI. You can issue this call multiple times to
	 * get the current status.
	 * 
	 * @param apiContext
	 *            {@link APIContext} used for the API call.
	 * @param payoutBatchId
	 *            String
	 * @return PayoutBatch
	 * @throws PayPalRESTException
	 */
	public static PayoutBatch get(APIContext apiContext, String payoutBatchId)
			throws PayPalRESTException {
		if (payoutBatchId == null) {
			throw new IllegalArgumentException("payoutBatchId cannot be null");
		}
		Object[] parameters = new Object[] { payoutBatchId };
		String pattern = "v1/payments/payouts/{0}";
		String resourcePath = RESTUtil.formatURIPath(pattern, parameters);
		String payLoad = "";
		return configureAndExecute(apiContext, HttpMethod.GET,
				resourcePath, payLoad, PayoutBatch.class);
	}

}

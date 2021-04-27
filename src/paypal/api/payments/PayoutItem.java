package paypal.api.payments;

import paypal.base.rest.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PayoutItem extends PayPalResource {

	/**
	 * The type of ID that identifies the payment receiver. Value is:<ul><code>EMAIL</code>. Unencrypted email. Value is a string of up to 127 single-byte characters.</li><li><code>PHONE</code>. Unencrypted phone number.<blockquote><strong>Note:</strong> The PayPal sandbox does not support the <code>PHONE</code> recipient type.</blockquote></li><li><code>PAYPAL_ID</code>. Encrypted PayPal account number.</li></ul>If the <code>sender_batch_header</code> includes the <code>recipient_type</code> attribute, any payout item without its own <code>recipient_type</code> attribute uses the <code>recipient_type</code> value from <code>sender_batch_header</code>. If the <code>sender_batch_header</code> omits the <code>recipient_type</code> attribute, each payout item must include its own <code>recipient_type</code> value.
	 */
	private String recipientType;

	/**
	 * The amount of money to pay the receiver.
	 */
	private Currency amount;

	/**
	 * Optional. A sender-specified note for notifications. Value is any string value.
	 */
	private String note;

	/**
	 * The receiver of the payment. Corresponds to the `recipient_type` value in the request.
	 */
	private String receiver;

	/**
	 * A sender-specified ID number. Tracks the batch payout in an accounting system.
	 */
	private String senderItemId;

	/**
	 * Default Constructor
	 */
	public PayoutItem() {
	}

	/**
	 * Parameterized Constructor
	 */
	public PayoutItem(Currency amount, String receiver) {
		this.amount = amount;
		this.receiver = receiver;
	}

	/**
	 * Obtain the status of a payout item by passing the item ID to the request
	 * URI.
	 * @deprecated Please use {@link #get(APIContext, String)} instead.
	 *
	 * @param accessToken
	 *            Access Token used for the API call.
	 * @param payoutItemId
	 *            String
	 * @return PayoutItemDetails
	 * @throws PayPalRESTException
	 */
	public static PayoutItemDetails get(String accessToken, String payoutItemId)
			throws PayPalRESTException {
		APIContext apiContext = new APIContext(accessToken);
		return get(apiContext, payoutItemId);
	}

	/**
	 * Obtain the status of a payout item by passing the item ID to the request
	 * URI.
	 *
	 * @param apiContext
	 *            {@link APIContext} used for the API call.
	 * @param payoutItemId
	 *            String
	 * @return PayoutItemDetails
	 * @throws PayPalRESTException
	 */
	public static PayoutItemDetails get(APIContext apiContext,
			String payoutItemId) throws PayPalRESTException {
		if (payoutItemId == null) {
			throw new IllegalArgumentException("payoutItemId cannot be null");
		}
		Object[] parameters = new Object[] { payoutItemId };
		String pattern = "v1/payments/payouts-item/{0}";
		String resourcePath = RESTUtil.formatURIPath(pattern, parameters);
		String payLoad = "";
		return configureAndExecute(apiContext, HttpMethod.GET,
				resourcePath, payLoad, PayoutItemDetails.class);
	}

	/**
	 * Cancels the unclaimed payment using the items id passed in the request
	 * URI. If an unclaimed item is not claimed within 30 days, the funds will
	 * be automatically returned to the sender. This call can be used to cancel
	 * the unclaimed item prior to the automatic 30-day return.
	 * @deprecated Please use {@link #cancel(APIContext, String)} instead.
	 *
	 * @param accessToken
	 *            Access Token used for the API call.
	 * @param payoutItemId
	 *            String
	 * @return PayoutItemDetails
	 * @throws PayPalRESTException
	 */
	public static PayoutItemDetails cancel(String accessToken,
			String payoutItemId) throws PayPalRESTException {
		APIContext apiContext = new APIContext(accessToken);
		return cancel(apiContext, payoutItemId);
	}

	/**
	 * Cancels the unclaimed payment using the items id passed in the request
	 * URI. If an unclaimed item is not claimed within 30 days, the funds will
	 * be automatically returned to the sender. This call can be used to cancel
	 * the unclaimed item prior to the automatic 30-day return.
	 *
	 * @param apiContext
	 *            {@link APIContext} used for the API call.
	 * @param payoutItemId
	 *            String
	 * @return PayoutItemDetails
	 * @throws PayPalRESTException
	 */
	public static PayoutItemDetails cancel(APIContext apiContext,
			String payoutItemId) throws PayPalRESTException {
		if (payoutItemId == null) {
			throw new IllegalArgumentException("payoutItemId cannot be null");
		}
		Object[] parameters = new Object[] { payoutItemId };
		String pattern = "v1/payments/payouts-item/{0}/cancel";
		String resourcePath = RESTUtil.formatURIPath(pattern, parameters);
		String payLoad = "";
		return configureAndExecute(apiContext, HttpMethod.POST,
				resourcePath, payLoad, PayoutItemDetails.class);
	}
}

package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.Getter; import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class MerchantPreferences  extends PayPalModel {

	/**
	 * Identifier of the merchant_preferences. 128 characters max.
	 */
	private String id;

	/**
	 * Setup fee amount. Default is 0.
	 */
	private Currency setupFee;

	/**
	 * Redirect URL on cancellation of agreement request. 1000 characters max.
	 */
	private String cancelUrl;

	/**
	 * Redirect URL on creation of agreement request. 1000 characters max.
	 */
	private String returnUrl;

	/**
	 * Notify URL on agreement creation. 1000 characters max.
	 */
	private String notifyUrl;

	/**
	 * Total number of failed attempts allowed. Default is 0, representing an infinite number of failed attempts.
	 */
	private String maxFailAttempts;

	/**
	 * Allow auto billing for the outstanding amount of the agreement in the next cycle. Allowed values: `YES`, `NO`. Default is `NO`.
	 */
	private String autoBillAmount;

	/**
	 * Action to take if a failure occurs during initial payment. Allowed values: `CONTINUE`, `CANCEL`. Default is continue.
	 */
	private String initialFailAmountAction;

	/**
	 * Payment types that are accepted for this plan.
	 */
	private String acceptedPaymentType;

	/**
	 * char_set for this plan.
	 */
	private String charSet;

	/**
	 * Default Constructor
	 */
	public MerchantPreferences() {
	}

	/**
	 * Parameterized Constructor
	 */
	public MerchantPreferences(String cancelUrl, String returnUrl) {
		this.cancelUrl = cancelUrl;
		this.returnUrl = returnUrl;
	}
}

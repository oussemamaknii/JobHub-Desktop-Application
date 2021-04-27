package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.Getter; import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AlternatePayment extends PayPalModel {

	/**
	 * The unique identifier of the alternate payment account.
	 */
	private String alternatePaymentAccountId;

	/**
	 * The unique identifier of the payer
	 */
	private String externalCustomerId;

	/**
	 * Alternate Payment provider id. This is an optional attribute needed only for certain alternate providers e.g Ideal
	 */
	private String alternatePaymentProviderId;

	/**
	 * Default Constructor
	 */
	public AlternatePayment() {
	}

	/**
	 * Parameterized Constructor
	 */
	public AlternatePayment(String alternatePaymentAccountId) {
		this.alternatePaymentAccountId = alternatePaymentAccountId;
	}
}

package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.Getter; import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PaymentCardToken  extends PayPalModel {

	/**
	 * ID of a previously saved Payment Card resource.
	 */
	private String paymentCardId;

	/**
	 * The unique identifier of the payer used when saving this payment card.
	 */
	private String externalCustomerId;

	/**
	 * Last 4 digits of the card number from the saved card.
	 */
	private String last4;

	/**
	 * Type of the Card.
	 */
	private String type;

	/**
	 * card expiry month from the saved card with value 1 - 12
	 */
	private int expireMonth;

	/**
	 * 4 digit card expiry year from the saved card
	 */
	private int expireYear;

	/**
	 * Default Constructor
	 */
	public PaymentCardToken() {
	}

	/**
	 * Parameterized Constructor
	 */
	public PaymentCardToken(String paymentCardId, String externalCustomerId, String type) {
		this.paymentCardId = paymentCardId;
		this.externalCustomerId = externalCustomerId;
		this.type = type;
	}
}

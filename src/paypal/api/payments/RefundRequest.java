package paypal.api.payments;

import paypal.base.rest.PayPalModel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RefundRequest extends PayPalModel {

	/**
	 * Details including both refunded amount (to payer) and refunded fee (to payee).
	 */
	private Amount amount;

	/**
	 * Description of what is being refunded for. Character length and limitations: 255 single-byte alphanumeric characters.
	 */
	private String description;

	/**
	 * Type of refund you are making.
	 */
	private String refundType;

	/**
	 * Type of PayPal funding source (balance or eCheck) that can be used for auto refund.
	 */
	private String refundSource;

	/**
	 * Reason description for the Sale transaction being refunded.
	 */
	private String reason;

	/**
	 * The invoice number that is used to track this payment. Character length and limitations: 127 single-byte alphanumeric characters.
	 */
	private String invoiceNumber;

	/**
	 * Flag to indicate that the buyer was already given store credit for a given transaction.
	 */
	private Boolean refundAdvice;

	/**
	 * It indicates that the resource id passed is not processed by payments platform
	 */
	private String isNonPlatformTransaction;

	/**
	 * Default Constructor
	 */
	public RefundRequest() {
	}

}

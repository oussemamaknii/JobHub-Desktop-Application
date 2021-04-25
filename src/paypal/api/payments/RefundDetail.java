package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RefundDetail extends PayPalModel {

	/**
	 * The PayPal refund type. Indicates whether refund was paid in invoicing flow through PayPal or externally. In the case of mark-as-refunded API, the supported refund type is `EXTERNAL`. For backward compatability, the `PAYPAL` refund type is still supported.
	 */
	private String type;

	/**
	 * The PayPal refund transaction ID. Required with the `PAYPAL` refund type.
	 */
	private String transactionId;

	/**
	 * Date on which the invoice was refunded. Date format: yyyy-MM-dd z. For example, 2014-02-27 PST.
	 */
	private String date;

	/**
	 * Optional note associated with the refund.
	 */
	private String note;

	/**
	 * Amount to be recorded as refund against invoice. If this field is not passed, the total invoice paid amount is recorded as refund.
	 */
	private Currency amount;

	/**
	 * Default Constructor
	 */
	public RefundDetail() {
	}

	/**
	 * Parameterized Constructor
	 */
	public RefundDetail(String type) {
		this.type = type;
	}
}

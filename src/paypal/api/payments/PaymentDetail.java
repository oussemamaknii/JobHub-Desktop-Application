package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PaymentDetail extends PayPalModel {

	/**
	 * The PayPal payment detail. Indicates whether payment was made in an invoicing flow through PayPal or externally. In the case of the mark-as-paid API, the supported payment type is `EXTERNAL`. For backward compatibility, the `PAYPAL` payment type is still supported.
	 */
	private String type;

	/**
	 * The PayPal payment transaction ID. Required with the `PAYPAL` payment type.
	 */
	private String transactionId;

	/**
	 * Type of the transaction.
	 */
	private String transactionType;

	/**
	 * The date when the invoice was paid. The date format is *yyyy*-*MM*-*dd* *z* as defined in [Internet Date/Time Format](http://tools.ietf.org/html/rfc3339#section-5.6).
	 */
	private String date;

	/**
	 * The payment mode or method. Required with the `EXTERNAL` payment type.
	 */
	private String method;

	/**
	 * Optional. A note associated with the payment.
	 */
	private String note;

	/**
	 * The amount to record as payment against invoice. If you omit this parameter, the total invoice amount is recorded as payment.
	 */
	private Currency amount;

	/**
	 * Default Constructor
	 */
	public PaymentDetail() {
	}

	/**
	 * Parameterized Constructor
	 */
	public PaymentDetail(String method) {
		this.method = method;
	}
}

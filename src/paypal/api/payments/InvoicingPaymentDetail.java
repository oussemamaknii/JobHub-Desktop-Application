package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.Getter; import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class InvoicingPaymentDetail  extends PayPalModel {

	/**
	 * PayPal payment detail indicating whether payment was made in an invoicing flow via PayPal or externally. In the case of the mark-as-paid API, payment type is EXTERNAL and this is what is now supported. The PAYPAL value is provided for backward compatibility.
	 */
	private String type;

	/**
	 * PayPal payment transaction id. Mandatory field in case the type value is PAYPAL.
	 */
	private String transactionId;

	/**
	 * Type of the transaction.
	 */
	private String transactionType;

	/**
	 * Date when the invoice was paid. Date format: yyyy-MM-dd z. For example, 2014-02-27 PST.
	 */
	private String date;

	/**
	 * Payment mode or method. This field is mandatory if the value of the type field is OTHER.
	 */
	private String method;

	/**
	 * Optional note associated with the payment.
	 */
	private String note;

	/**
	 * Default Constructor
	 */
	public InvoicingPaymentDetail() {
	}

	/**
	 * Parameterized Constructor
	 */
	public InvoicingPaymentDetail(String method) {
		this.method = method;
	}
}

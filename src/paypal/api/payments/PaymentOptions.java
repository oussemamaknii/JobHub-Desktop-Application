package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PaymentOptions extends PayPalModel {

	/**
	 * Payment method requested for this purchase unit
	 */
	private String allowedPaymentMethod;

	/**
	 * Indicator if this payment request is a recurring payment. Only supported when the `payment_method` is set to `credit_card`
	 */
	private Boolean recurringFlag;

	/**
	 * Indicator if fraud management filters (fmf) should be skipped for this transaction. Only supported when the `payment_method` is set to `credit_card`
	 */
	private Boolean skipFmf;

	/**
	 * Default Constructor
	 */
	public PaymentOptions() {
	}

}

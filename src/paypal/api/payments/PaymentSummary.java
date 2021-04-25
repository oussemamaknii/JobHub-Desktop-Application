package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PaymentSummary extends PayPalModel {

	/**
	 * Total Amount paid/refunded via PayPal.
	 */
	private Currency paypal;

	/**
	 * Total Amount paid/refunded via other sources.
	 */
	private Currency other;

	/**
	 * Default Constructor
	 */
	public PaymentSummary() {
	}

}

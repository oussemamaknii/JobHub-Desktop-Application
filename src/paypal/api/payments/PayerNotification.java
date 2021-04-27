package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PayerNotification extends PayPalModel {

	/**
	 * Email Address associated with the Payer's PayPal Account.
	 */
	private String email;

	/**
	 * Default Constructor
	 */
	public PayerNotification() {
	}

}

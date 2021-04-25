package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.Getter; import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AgreementStateDescriptor  extends PayPalModel {

	/**
	 * Reason for changing the state of the agreement.
	 */
	private String note;

	/**
	 * The amount and currency of the agreement.
	 */
	private Currency amount;

	/**
	 * Default Constructor
	 */
	public AgreementStateDescriptor() {
	}

	/**
	 * Parameterized Constructor
	 */
	public AgreementStateDescriptor(Currency amount) {
		this.amount = amount;
	}
}

package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.Getter; import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Transactions  extends PayPalModel {

	/**
	 * Amount being collected.
	 */
	private Amount amount;

	/**
	 * Default Constructor
	 */
	public Transactions() {
	}
}

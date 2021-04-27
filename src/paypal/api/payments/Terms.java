package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.Getter; import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Terms  extends PayPalModel {

	/**
	 * Identifier of the terms. 128 characters max.
	 */
	private String id;

	/**
	 * Term type. Allowed values: `MONTHLY`, `WEEKLY`, `YEARLY`.
	 */
	private String type;

	/**
	 * Max Amount associated with this term.
	 */
	private Currency maxBillingAmount;

	/**
	 * How many times money can be pulled during this term.
	 */
	private String occurrences;

	/**
	 * Amount_range associated with this term.
	 */
	private Currency amountRange;

	/**
	 * Buyer's ability to edit the amount in this term.
	 */
	private String buyerEditable;

	/**
	 * Default Constructor
	 */
	public Terms() {
	}

	/**
	 * Parameterized Constructor
	 */
	public Terms(String type, Currency maxBillingAmount, String occurrences, Currency amountRange, String buyerEditable) {
		this.type = type;
		this.maxBillingAmount = maxBillingAmount;
		this.occurrences = occurrences;
		this.amountRange = amountRange;
		this.buyerEditable = buyerEditable;
	}
}

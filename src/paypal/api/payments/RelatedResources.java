package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RelatedResources extends PayPalModel {

	/**
	 * Sale transaction
	 */
	private Sale sale;

	/**
	 * Authorization transaction
	 */
	private Authorization authorization;

	/**
	 * Order transaction
	 */
	private Order order;

	/**
	 * Capture transaction
	 */
	private Capture capture;

	/**
	 * Refund transaction
	 */
	private Refund refund;

	/**
	 * Default Constructor
	 */
	public RelatedResources() {
	}

}

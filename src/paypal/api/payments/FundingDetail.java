package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class FundingDetail extends PayPalModel {

	/**
	 * Expected clearing time
	 */
	private String clearingTime;

	/**
	 * [DEPRECATED] Hold-off duration of the payment. payment_debit_date should be used instead.
	 */
	private String paymentHoldDate;

	/**
	 * Date when funds will be debited from the payer's account
	 */
	private String paymentDebitDate;

	/**
	 * Processing type of the payment card
	 */
	private String processingType;

	/**
	 * Default Constructor
	 */
	public FundingDetail() {
	}

}

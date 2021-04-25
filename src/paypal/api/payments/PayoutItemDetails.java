package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PayoutItemDetails extends PayPalModel {

	/**
	 * The ID for the payout item. Viewable when you show details for a batch payout.
	 */
	private String payoutItemId;

	/**
	 * The PayPal-generated ID for the transaction.
	 */
	private String transactionId;

	/**
	 * The transaction status.
	 */
	private String transactionStatus;

	/**
	 * The amount of money, in U.S. dollars, for fees.
	 */
	private Currency payoutItemFee;

	/**
	 * The PayPal-generated ID for the batch payout.
	 */
	private String payoutBatchId;

	/**
	 * A sender-specified ID number. Tracks the batch payout in an accounting system.
	 */
	private String senderBatchId;

	/**
	 * The sender-provided information for the payout item.
	 */
	private PayoutItem payoutItem;

	/**
	 * The date and time when this item was last processed.
	 */
	private String timeProcessed;

	/**
	 * 
	 */
	private Error errors;

	/**
	 * The HATEOAS links related to the call.
	 */
	private List<Links> links;

	/**
	 * Default Constructor
	 */
	public PayoutItemDetails() {
	}

	/**
	 * Parameterized Constructor
	 */
	public PayoutItemDetails(String payoutItemId, String payoutBatchId,
			PayoutItem payoutItem, String timeProcessed) {
		this.payoutItemId = payoutItemId;
		this.payoutBatchId = payoutBatchId;
		this.payoutItem = payoutItem;
		this.timeProcessed = timeProcessed;
	}

	/**
	 * Setter for error. Please use this over {@link #setErrors(Error)}.
	 * errors field in {@link PayoutItemDetails} takes one {@link Error} object.
	 * Not using lombok autogeneration as `setErrors` is not a feasible option.
	 */
	public PayoutItemDetails setError(Error error) {
		this.errors = error;
		return this;
	}

	/**
	 * Getter for error. Please use this over {@link #getErrors()}.
	 * errors field in {@link PayoutItemDetails} takes one {@link Error} object.
	 */
	public Error getError() {
		return this.errors;
	}

}

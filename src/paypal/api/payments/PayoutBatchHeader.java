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
public class PayoutBatchHeader extends PayPalModel {

	/**
	 * The PayPal-generated ID for a batch payout.
	 */
	private String payoutBatchId;

	/**
	 * The PayPal-generated batch payout status. If the batch payout passes the preliminary checks, the status is `PENDING`.
	 */
	private String batchStatus;

	/**
	 * The time the batch entered processing.
	 */
	private String timeCreated;

	/**
	 * The time that processing for the batch was completed.
	 */
	private String timeCompleted;

	/**
	 * The original batch header as provided by the payment sender.
	 */
	private PayoutSenderBatchHeader senderBatchHeader;

	/**
	 * Total amount, in U.S. dollars, requested for the applicable payouts.
	 */
	private Currency amount;

	/**
	 * Total estimate in U.S. dollars for the applicable payouts fees.
	 */
	private Currency fees;

	/**
	 *
	 */
	private Error errors;

	/**
	 *
	 */
	private List<Links> links;

	/**
	 * Default Constructor
	 */
	public PayoutBatchHeader() {
	}

	/**
	 * Parameterized Constructor
	 */
	public PayoutBatchHeader(String payoutBatchId, String batchStatus, String timeCreated, PayoutSenderBatchHeader senderBatchHeader, Currency amount, Currency fees) {
		this.payoutBatchId = payoutBatchId;
		this.batchStatus = batchStatus;
		this.timeCreated = timeCreated;
		this.senderBatchHeader = senderBatchHeader;
		this.amount = amount;
		this.fees = fees;
	}
}

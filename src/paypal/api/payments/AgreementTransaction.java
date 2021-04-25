package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.Getter; import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AgreementTransaction  extends PayPalModel {

	/**
	 * Id corresponding to this transaction.
	 */
	private String transactionId;

	/**
	 * State of the subscription at this time.
	 */
	private String status;

	/**
	 * Type of transaction, usually Recurring Payment.
	 */
	private String transactionType;

	/**
	 * Amount for this transaction.
	 */
	private Currency amount;

	/**
	 * Fee amount for this transaction.
	 */
	private Currency feeAmount;

	/**
	 * Net amount for this transaction.
	 */
	private Currency netAmount;

	/**
	 * Email id of payer.
	 */
	private String payerEmail;

	/**
	 * Business name of payer.
	 */
	private String payerName;

	/**
	 * Time zone of time_updated field.
	 */
	private String timeZone;
	
	/**
	 * Time at which this transaction happened.
	 */
	private String timeStamp;

	/**
	 * Default Constructor
	 */
	public AgreementTransaction() {
	}

	/**
	 * Parameterized Constructor
	 */
	public AgreementTransaction(Currency amount, Currency feeAmount, Currency netAmount) {
		this.amount = amount;
		this.feeAmount = feeAmount;
		this.netAmount = netAmount;
	}
	
	/**
	 * @deprecated use setTimeStamp instead.
	 * Setter for timeUpdated
	 */
	public AgreementTransaction setTimeUpdated(String timeUpdated) {
		this.timeStamp = timeUpdated;
		return this;
	}

	/**
	 * @deprecated use getTimeStamp instead.
	 * Getter for timeUpdated
	 */
	public String getTimeUpdated() {
		return this.timeStamp;
	}
}

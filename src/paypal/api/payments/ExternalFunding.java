package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ExternalFunding extends PayPalModel {

	/**
	 * Unique identifier for the external funding
	 */
	private String referenceId;

	/**
	 * Generic identifier for the external funding
	 */
	private String code;

	/**
	 * Encrypted PayPal Account identifier for the funding account
	 */
	private String fundingAccountId;

	/**
	 * Description of the external funding being applied
	 */
	private String displayText;

	/**
	 * Amount being funded by the external funding account
	 */
	private Amount amount;

	/**
	 * Indicates that the Payment should be fully funded by External Funded Incentive
	 */
	private String fundingInstruction;

	/**
	 * Default Constructor
	 */
	public ExternalFunding() {
	}

	/**
	 * Parameterized Constructor
	 */
	public ExternalFunding(String referenceId, String fundingAccountId, Amount amount) {
		this.referenceId = referenceId;
		this.fundingAccountId = fundingAccountId;
		this.amount = amount;
	}
}

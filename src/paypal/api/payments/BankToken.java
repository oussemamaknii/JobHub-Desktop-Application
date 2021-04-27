package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BankToken extends PayPalModel {

	/**
	 * ID of a previously saved Bank resource using /vault/bank API.
	 */
	private String bankId;

	/**
	 * The unique identifier of the payer used when saving this bank using /vault/bank API.
	 */
	private String externalCustomerId;

	/**
	 * Identifier of the direct debit mandate to validate. Currently supported only for EU bank accounts(SEPA).
	 */
	private String mandateReferenceNumber;

	/**
	 * Default Constructor
	 */
	public BankToken() {
	}

	/**
	 * Parameterized Constructor
	 */
	public BankToken(String bankId, String externalCustomerId) {
		this.bankId = bankId;
		this.externalCustomerId = externalCustomerId;
	}
}

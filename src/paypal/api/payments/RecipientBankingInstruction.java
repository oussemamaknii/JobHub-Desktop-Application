package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RecipientBankingInstruction extends PayPalModel {

	/**
	 * Name of the financial institution.
	 */
	private String bankName;

	/**
	 * Name of the account holder
	 */
	private String accountHolderName;

	/**
	 * bank account number
	 */
	private String accountNumber;

	/**
	 * bank routing number
	 */
	private String routingNumber;

	/**
	 * IBAN equivalent of the bank
	 */
	private String internationalBankAccountNumber;

	/**
	 * BIC identifier of the financial institution
	 */
	private String bankIdentifierCode;

	/**
	 * Default Constructor
	 */
	public RecipientBankingInstruction() {
	}

	/**
	 * Parameterized Constructor
	 */
	public RecipientBankingInstruction(String bankName, String accountHolderName, String internationalBankAccountNumber) {
		this.bankName = bankName;
		this.accountHolderName = accountHolderName;
		this.internationalBankAccountNumber = internationalBankAccountNumber;
	}
}

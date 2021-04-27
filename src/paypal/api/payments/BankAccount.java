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
public class BankAccount extends PayPalModel {

	/**
	 * ID of the bank account being saved for later use.
	 */
	private String id;

	/**
	 * Account number in either IBAN (max length 34) or BBAN (max length 17) format.
	 */
	private String accountNumber;

	/**
	 * Type of the bank account number (International or Basic Bank Account Number). For more information refer to http://en.wikipedia.org/wiki/International_Bank_Account_Number.
	 */
	private String accountNumberType;

	/**
	 * Routing transit number (aka Bank Code) of the bank (typically for domestic use only - for international use, IBAN includes bank code). For more information refer to http://en.wikipedia.org/wiki/Bank_code.
	 */
	private String routingNumber;

	/**
	 * Type of the bank account.
	 */
	private String accountType;

	/**
	 * A customer designated name.
	 */
	private String accountName;

	/**
	 * Type of the check when this information was obtained through a check by the facilitator or merchant.
	 */
	private String checkType;

	/**
	 * How the check was obtained from the customer, if check was the source of the information provided.
	 */
	private String authType;

	/**
	 * Time at which the authorization (or check) was captured. Use this field if the user authorization needs to be captured due to any privacy requirements.
	 */
	private String authCaptureTimestamp;

	/**
	 * Name of the bank.
	 */
	private String bankName;

	/**
	 * 2 letter country code of the Bank.
	 */
	private String countryCode;

	/**
	 * Account holder's first name.
	 */
	private String firstName;

	/**
	 * Account holder's last name.
	 */
	private String lastName;

	/**
	 * Birth date of the bank account holder.
	 */
	private String birthDate;

	/**
	 * Billing address.
	 */
	private Address billingAddress;

	/**
	 * State of this funding instrument.
	 */
	private String state;

	/**
	 * Confirmation status of a bank account.
	 */
	private String confirmationStatus;

	/**
	 * [DEPRECATED] Use external_customer_id instead.
	 */
	private String payerId;

	/**
	 * A unique identifier of the customer to whom this bank account belongs to. Generated and provided by the facilitator. This is required when creating or using a stored funding instrument in vault.
	 */
	private String externalCustomerId;

	/**
	 * A unique identifier of the merchant for which this bank account has been stored for. Generated and provided by the facilitator so it can be used to restrict the usage of the bank account to the specific merchant.
	 */
	private String merchantId;

	/**
	 * Time the resource was created.
	 */
	private String createTime;

	/**
	 * Time the resource was last updated.
	 */
	private String updateTime;

	/**
	 * Date/Time until this resource can be used to fund a payment.
	 */
	private String validUntil;

	/**
	 * 
	 */
	private List<DefinitionsLinkdescription> links;

	/**
	 * Default Constructor
	 */
	public BankAccount() {
	}

	/**
	 * Parameterized Constructor
	 */
	public BankAccount(String accountNumber, String accountNumberType) {
		this.accountNumber = accountNumber;
		this.accountNumberType = accountNumberType;
	}
}

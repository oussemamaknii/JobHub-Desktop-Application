package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PayerInfo extends PayPalModel {

	/**
	 * Email address representing the payer. 127 characters max.
	 */
	private String email;

	/**
	 * External Remember Me id representing the payer
	 */
	private String externalRememberMeId;

	/**
	 * @deprecated use {@link #buyerAccountNumber} instead
	 */
	@Deprecated
	private String accountNumber;

	/**
	 * Account Number representing the Payer
	 */
	private String buyerAccountNumber;

	/**
	 * Salutation of the payer.
	 */
	private String salutation;

	/**
	 * First name of the payer.
	 */
	private String firstName;

	/**
	 * Middle name of the payer.
	 */
	private String middleName;

	/**
	 * Last name of the payer.
	 */
	private String lastName;

	/**
	 * Suffix of the payer.
	 */
	private String suffix;

	/**
	 * PayPal assigned encrypted Payer ID.
	 */
	private String payerId;

	/**
	 * Phone number representing the payer. 20 characters max.
	 */
	private String phone;

	/**
	 * Phone type
	 */
	private String phoneType;

	/**
	 * Birth date of the Payer in ISO8601 format (yyyy-mm-dd).
	 */
	private String birthDate;

	/**
	 * Payer’s tax ID. Only supported when the `payment_method` is set to `paypal`.
	 */
	private String taxId;

	/**
	 * Payer’s tax ID type. Allowed values: `BR_CPF` or `BR_CNPJ`. Only supported when the `payment_method` is set to `paypal`.
	 */
	private String taxIdType;

	/**
	 * Two-letter registered country code of the payer to identify the buyer country.
	 */
	private String countryCode;

	/**
	 * Billing address of the Payer.
	 */
	private Address billingAddress;

	/**
	 * @deprecated  Use shipping address present in purchase unit or at root level of checkout Session.
	 */
	@Deprecated
	private ShippingAddress shippingAddress;

	/**
	 * Default Constructor
	 */
	public PayerInfo() {
	}

}

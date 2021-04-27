package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CarrierAccount extends PayPalModel {

	/**
	 * The ID of the carrier account of the payer. Use in subsequent REST API calls. For example, to make payments.
	 */
	private String id;

	/**
	 * The phone number of the payer, in E.164 format.
	 */
	private String phoneNumber;

	/**
	 * The ID of the customer, as created by the merchant.
	 */
	private String externalCustomerId;

	/**
	 * The method used to obtain the phone number. Value is `READ_FROM_DEVICE` or `USER_PROVIDED`.
	 */
	private String phoneSource;

	/**
	 * The ISO 3166-1 alpha-2 country code where the phone number is registered.
	 */
	private CountryCode countryCode;

	/**
	 * Default Constructor
	 */
	public CarrierAccount() {
	}

	/**
	 * Parameterized Constructor
	 */
	public CarrierAccount(String externalCustomerId, CountryCode countryCode) {
		this.externalCustomerId = externalCustomerId;
		this.countryCode = countryCode;
	}
}

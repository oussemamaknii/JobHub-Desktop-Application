package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BaseAddress extends PayPalModel {

	/**
	 * Line 1 of the Address (eg. number, street, etc).
	 */
	private String line1;

	/**
	 * Optional line 2 of the Address (eg. suite, apt #, etc.).
	 */
	private String line2;

	/**
	 * City name.
	 */
	private String city;

	/**
	 * 2 letter country code.
	 */
	private String countryCode;

	/**
	 * Zip code or equivalent is usually required for countries that have them. For list of countries that do not have postal codes please refer to http://en.wikipedia.org/wiki/Postal_code.
	 */
	private String postalCode;

	/**
	 * 2 letter code for US states, and the equivalent for other countries.
	 */
	private String state;

	/**
	 * BaseAddress normalization status, returned only for payers from Brazil.
	 */
	private String normalizationStatus;

	/**
	 * BaseAddress status
	 */
	private String status;

	/**
	 * Default Constructor
	 */
	public BaseAddress() {
	}

	/**
	 * Parameterized Constructor
	 */
	public BaseAddress(String line1, String countryCode) {
		this.line1 = line1;
		this.countryCode = countryCode;
	}
}

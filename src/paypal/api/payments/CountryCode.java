package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CountryCode extends PayPalModel {

	/**
	 * ISO country code based on 2-character IS0-3166-1 codes.
	 */
	private String countryCode;

	/**
	 * Default Constructor
	 */
	public CountryCode() {
	}

	/**
	 * Parameterized Constructor
	 */
	public CountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
}

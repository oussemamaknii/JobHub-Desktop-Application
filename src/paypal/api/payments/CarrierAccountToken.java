package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CarrierAccountToken extends PayPalModel {

	/**
	 * ID of a previously saved carrier account resource.
	 */
	private String carrierAccountId;

	/**
	 * The unique identifier of the payer used when saving this carrier account instrument.
	 */
	private String externalCustomerId;

	/**
	 * Default Constructor
	 */
	public CarrierAccountToken() {
	}

	/**
	 * Parameterized Constructor
	 */
	public CarrierAccountToken(String carrierAccountId, String externalCustomerId) {
		this.carrierAccountId = carrierAccountId;
		this.externalCustomerId = externalCustomerId;
	}
}

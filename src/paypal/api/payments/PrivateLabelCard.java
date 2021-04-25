package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PrivateLabelCard extends PayPalModel {

	/**
	 * encrypted identifier of the private label card instrument.
	 */
	private String id;

	/**
	 * last 4 digits of the card number.
	 */
	private String cardNumber;

	/**
	 * Merchants providing private label store cards have associated issuer account. This value indicates encrypted account number of the associated issuer account.
	 */
	private String issuerId;

	/**
	 * Merchants providing private label store cards have associated issuer account. This value indicates name on the issuer account.
	 */
	private String issuerName;

	/**
	 * This value indicates URL to access PLCC program logo image
	 */
	private String imageKey;

	/**
	 * Default Constructor
	 */
	public PrivateLabelCard() {
	}

}

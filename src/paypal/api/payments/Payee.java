package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Payee extends PayPalModel {

	/**
	 * Email Address associated with the Payee's PayPal Account. If the provided email address is not associated with any PayPal Account, the payee can only receive PayPal Wallet Payments. Direct Credit Card Payments will be denied due to card compliance requirements.
	 */
	private String email;

	/**
	 * Encrypted PayPal account identifier for the Payee.
	 */
	private String merchantId;

	/**
	 * First Name of the Payee.
	 */
	private String firstName;

	/**
	 * Last Name of the Payee.
	 */
	private String lastName;

	/**
	 * Unencrypted PayPal account Number of the Payee
	 */
	private String accountNumber;

	/**
	 * Information related to the Payee.
	 */
	private Phone phone;

	/**
	 * Default Constructor
	 */
	public Payee() {
	}
}

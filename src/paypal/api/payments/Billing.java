package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Billing extends PayPalModel {

	/**
	 * Identifier of the instrument in PayPal Wallet
	 */
	private String billingAgreementId;

	/**
	 * Selected installment option for issuer based installments (BR and MX).
	 */
	private InstallmentOption selectedInstallmentOption;

	/**
	 * Default Constructor
	 */
	public Billing() {
	}

}

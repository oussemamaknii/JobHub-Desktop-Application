package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class FundingInstrument extends PayPalModel {

	/**
	 * Credit Card instrument.
	 */
	private CreditCard creditCard;

	/**
	 * PayPal vaulted credit Card instrument.
	 */
	private CreditCardToken creditCardToken;

	/**
	 * Payment Card information.
	 */
	private PaymentCard paymentCard;

	/**
	 * Bank Account information.
	 */
	private ExtendedBankAccount bankAccount;

	/**
	 * Vaulted bank account instrument.
	 */
	private BankToken bankAccountToken;

	/**
	 * PayPal credit funding instrument.
	 */
	private Credit credit;

	/**
	 * Incentive funding instrument.
	 */
	private Incentive incentive;

	/**
	 * External funding instrument.
	 */
	private ExternalFunding externalFunding;

	/**
	 * Carrier account token instrument.
	 */
	private CarrierAccountToken carrierAccountToken;

	/**
	 * Carrier account instrument
	 */
	private CarrierAccount carrierAccount;

	/**
	 * Private Label Card funding instrument. These are store cards provided by merchants to drive business with value to customer with convenience and rewards.
	 */
	private PrivateLabelCard privateLabelCard;

	/**
	 * Billing instrument that references pre-approval information for the payment
	 */
	private Billing billing;

	/**
	 * Alternate Payment  information - Mostly regional payment providers. For e.g iDEAL in Netherlands
	 */
	private AlternatePayment alternatePayment;

	/**
	 * Default Constructor
	 */
	public FundingInstrument() {
	}

}

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
public class PaymentCard extends PayPalModel {

	/**
	 * The ID of a credit card to save for later use.
	 */
	private String id;

	/**
	 * The card number.
	 */
	private String number;

	/**
	 * The card type.
	 */
	private String type;

	/**
	 * The two-digit expiry month for the card.
	 */
	private String expireMonth;

	/**
	 * The four-digit expiry year for the card.
	 */
	private String expireYear;

	/**
	 * The two-digit start month for the card. Required for UK Maestro cards.
	 */
	private String startMonth;

	/**
	 * The four-digit start year for the card. Required for UK Maestro cards. 
	 */
	private String startYear;

	/**
	 * The validation code for the card. Supported for payments but not for saving payment cards for future use.
	 */
	private String cvv2;

	/**
	 * The first name of the card holder.
	 */
	private String firstName;

	/**
	 * The last name of the card holder.
	 */
	private String lastName;

	/**
	 * The two-letter country code.
	 */
	private CountryCode billingCountry;

	/**
	 * The billing address for the card.
	 */
	private Address billingAddress;

	/**
	 * The ID of the customer who owns this card account. The facilitator generates and provides this ID. Required when you create or use a stored funding instrument in the PayPal vault.
	 */
	private String externalCustomerId;

	/**
	 * The state of the funding instrument.
	 */
	private String status;

	/**
	 * The product class of the financial instrument issuer.
	 */
	private String cardProductClass;

	/**
	 * The date and time until when this instrument can be used fund a payment.
	 */
	private String validUntil;

	/**
	 * The one- to two-digit card issue number. Required for UK Maestro cards.
	 */
	private String issueNumber;

	/**
	 * Fields required to support 3d secure information when processing credit card payments. Only supported when the `payment_method` is set to `credit_card`.
	 */
	private Card3dSecureInfo card3dSecureInfo;

	/**
	 * 
	 */
	private List<DefinitionsLinkdescription> links;

	/**
	 * Default Constructor
	 */
	public PaymentCard() {
	}

	/**
	 * @deprecated Please use {@link #setCard3dSecureInfo(Card3dSecureInfo)} instead.
	 *
	 * Setter for 3dSecureInfo
	 */
	@Deprecated
	public PaymentCard set3dSecureInfo(Card3dSecureInfo card3dSecureInfo) {
		this.card3dSecureInfo = card3dSecureInfo;
		return this;
	}

	/**
	 * @deprecated Please use {@link #getCard3dSecureInfo()} instead.
	 *
	 * Getter for 3dSecureInfo
	 */
	public Card3dSecureInfo get3dSecureInfo() {
		return this.card3dSecureInfo;
	}
}

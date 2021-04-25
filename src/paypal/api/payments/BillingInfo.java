package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BillingInfo extends PayPalModel {

	/**
	 * The invoice recipient email address. Maximum length is 260 characters.
	 */
	private String email;

	/**
	 * The invoice recipient first name. Maximum length is 30 characters.
	 */
	private String firstName;

	/**
	 * The invoice recipient last name. Maximum length is 30 characters.
	 */
	private String lastName;

	/**
	 * The invoice recipient company business name. Maximum length is 100 characters.
	 */
	private String businessName;

	/**
	 * The invoice recipient address.
	 */
	private InvoiceAddress address;

	/**
	 * The language in which the email was sent to the payer. Used only when the payer does not have a PayPal account.
	 */
	private String language;

	/**
	 * Additional information, such as business hours. Maximum length is 40 characters.
	 */
	private String additionalInfo;

	/**
	 * Preferred notification channel of the payer. Email by default.
	 */
	private String notificationChannel;

	/**
	 * Mobile Phone number of the recipient to which SMS will be sent if notification_channel is SMS.
	 */
	private Phone phone;

	/**
	 * Default Constructor
	 */
	public BillingInfo() {
	}

	/**
	 * Parameterized Constructor
	 */
	public BillingInfo(String email) {
		this.email = email;
	}
}

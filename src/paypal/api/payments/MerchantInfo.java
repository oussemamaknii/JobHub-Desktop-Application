package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class MerchantInfo extends PayPalModel {

	/**
	 * The merchant email address. Maximum length is 260 characters.
	 */
	private String email;

	/**
	 * The merchant first name. Maximum length is 30 characters.
	 */
	private String firstName;

	/**
	 * The merchant last name. Maximum length is 30 characters.
	 */
	private String lastName;

	/**
	 * The merchant address.
	 */
	private InvoiceAddress address;

	/**
	 * The merchant company business name. Maximum length is 100 characters.
	 */
	private String businessName;

	/**
	 * The merchant phone number.
	 */
	private Phone phone;

	/**
	 * The merchant fax number.
	 */
	private Phone fax;

	/**
	 * The merchant website. Maximum length is 2048 characters.
	 */
	private String website;

	/**
	 * The merchant tax ID. Maximum length is 100 characters.
	 */
	private String taxId;

	/**
	 * Option to provide a label to the additional_info field. 40 characters max.
	 */
	private String additionalInfoLabel;

	/**
	 * Additional information, such as business hours. Maximum length is 40 characters.
	 */
	private String additionalInfo;

	/**
	 * Default Constructor
	 */
	public MerchantInfo() {
	}

	/**
	 * Parameterized Constructor
	 */
	public MerchantInfo(String email) {
		this.email = email;
	}
}

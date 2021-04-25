package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Participant extends PayPalModel {

	/**
	 * The participant email address.
	 */
	private String email;

	/**
	 * The participant first name.
	 */
	private String firstName;

	/**
	 * The participant last name.
	 */
	private String lastName;

	/**
	 * The participant company business name.
	 */
	private String businessName;

	/**
	 * The participant phone number.
	 */
	private Phone phone;

	/**
	 * The participant fax number.
	 */
	private Phone fax;

	/**
	 * The participant website.
	 */
	private String website;

	/**
	 * Additional information, such as business hours.
	 */
	private String additionalInfo;

	/**
	 * The participant address.
	 */
	private InvoiceAddress address;

	/**
	 * Default Constructor
	 */
	public Participant() {
	}

	/**
	 * Parameterized Constructor
	 */
	public Participant(String email) {
		this.email = email;
	}
}

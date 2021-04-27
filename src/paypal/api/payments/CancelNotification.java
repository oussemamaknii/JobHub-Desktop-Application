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
public class CancelNotification extends PayPalModel {

	/**
	 * Subject of the notification.
	 */
	private String subject;

	/**
	 * Note to the payer.
	 */
	private String note;

	/**
	 * Indicates whether to send a copy of the notification to the merchant.
	 */
	private Boolean sendToMerchant;

	/**
	 * Indicates whether to send a copy of the notification to the payer.
	 */
	private Boolean sendToPayer;

	/**
	 * Applicable for invoices created with Cc emails. If this field is not in the body, all the cc email addresses added as part of the invoice shall be notified else this field can be used to limit the list of email addresses. Note: additional email addresses are not supported.
	 */
	private List<String> ccEmails;

	/**
	 * Default Constructor
	 */
	public CancelNotification() {
	}
}

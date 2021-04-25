package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Metadata extends PayPalModel {

	/**
	 * The date and time when the resource was created.
	 */
	private String createdDate;

	/**
	 * The email address of the account that created the resource.
	 */
	private String createdBy;

	/**
	 * The date and time when the resource was cancelled.
	 */
	private String cancelledDate;

	/**
	 * The actor who cancelled the resource.
	 */
	private String cancelledBy;

	/**
	 * The date and time when the resource was last edited.
	 */
	private String lastUpdatedDate;

	/**
	 * The email address of the account that last edited the resource.
	 */
	private String lastUpdatedBy;

	/**
	 * The date and time when the resource was first sent.
	 */
	private String firstSentDate;

	/**
	 * The date and time when the resource was last sent.
	 */
	private String lastSentDate;

	/**
	 * The email address of the account that last sent the resource.
	 */
	private String lastSentBy;

	/**
	 * URL representing the payer's view of the invoice.
	 */
	private String payerViewUrl;

	/**
	 * Default Constructor
	 */
	public Metadata() {
	}
}

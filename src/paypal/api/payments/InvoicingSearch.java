package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.Getter; import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class InvoicingSearch  extends PayPalModel {

	/**
	 * Initial letters of the email address.
	 */
	private String email;

	/**
	 * Initial letters of the recipient's first name.
	 */
	private String recipientFirstName;

	/**
	 * Initial letters of the recipient's last name.
	 */
	private String recipientLastName;

	/**
	 * Initial letters of the recipient's business name.
	 */
	private String recipientBusinessName;

	/**
	 * The invoice number that appears on the invoice.
	 */
	private String number;

	/**
	 * Status of the invoice.
	 */
	private String status;

	/**
	 * Lower limit of total amount.
	 */
	private Currency lowerTotalAmount;

	/**
	 * Upper limit of total amount.
	 */
	private Currency upperTotalAmount;

	/**
	 * Start invoice date.
	 */
	private String startInvoiceDate;

	/**
	 * End invoice date.
	 */
	private String endInvoiceDate;

	/**
	 * Start invoice due date.
	 */
	private String startDueDate;

	/**
	 * End invoice due date.
	 */
	private String endDueDate;

	/**
	 * Start invoice payment date.
	 */
	private String startPaymentDate;

	/**
	 * End invoice payment date.
	 */
	private String endPaymentDate;

	/**
	 * Start invoice creation date.
	 */
	private String startCreationDate;

	/**
	 * End invoice creation date.
	 */
	private String endCreationDate;

	/**
	 * Offset of the search results.
	 */
	private float page;

	/**
	 * Page size of the search results.
	 */
	private float pageSize;

	/**
	 * A flag indicating whether total count is required in the response.
	 */
	private Boolean totalCountRequired;

	/**
	 * Default Constructor
	 */
	public InvoicingSearch() {
	}
}

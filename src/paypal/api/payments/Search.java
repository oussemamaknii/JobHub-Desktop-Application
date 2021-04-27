package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Search extends PayPalModel {

	/**
	 * The initial letters of the email address.
	 */
	private String email;

	/**
	 * The initial letters of the recipient's first name.
	 */
	private String recipientFirstName;

	/**
	 * The initial letters of the recipient's last name.
	 */
	private String recipientLastName;

	/**
	 * The initial letters of the recipient's business name.
	 */
	private String recipientBusinessName;

	/**
	 * The invoice number.
	 */
	private String number;

	/**
	 * The invoice status.
	 */
	private String status;

	/**
	 * The lower limit of the total amount.
	 */
	private Currency lowerTotalAmount;

	/**
	 * The upper limit of total amount.
	 */
	private Currency upperTotalAmount;

	/**
	 * The start date for the invoice. Date format is *yyyy*-*MM*-*dd* *z*, as defined in [Internet Date/Time Format](http://tools.ietf.org/html/rfc3339#section-5.6).
	 */
	private String startInvoiceDate;

	/**
	 * The end date for the invoice. Date format is *yyyy*-*MM*-*dd* *z*, as defined in [Internet Date/Time Format](http://tools.ietf.org/html/rfc3339#section-5.6).
	 */
	private String endInvoiceDate;

	/**
	 * The start due date for the invoice. Date format is *yyyy*-*MM*-*dd* *z*, as defined in [Internet Date/Time Format](http://tools.ietf.org/html/rfc3339#section-5.6).
	 */
	private String startDueDate;

	/**
	 * The end due date for the invoice. Date format is *yyyy*-*MM*-*dd* *z*, as defined in [Internet Date/Time Format](http://tools.ietf.org/html/rfc3339#section-5.6).
	 */
	private String endDueDate;

	/**
	 * The start payment date for the invoice. Date format is *yyyy*-*MM*-*dd* *z*, as defined in [Internet Date/Time Format](http://tools.ietf.org/html/rfc3339#section-5.6).
	 */
	private String startPaymentDate;

	/**
	 * The end payment date for the invoice. Date format is *yyyy*-*MM*-*dd* *z*, as defined in [Internet Date/Time Format](http://tools.ietf.org/html/rfc3339#section-5.6).
	 */
	private String endPaymentDate;

	/**
	 * The start creation date for the invoice. Date format is *yyyy*-*MM*-*dd* *z*, as defined in [Internet Date/Time Format](http://tools.ietf.org/html/rfc3339#section-5.6).
	 */
	private String startCreationDate;

	/**
	 * The end creation date for the invoice. Date format is *yyyy*-*MM*-*dd* *z*, as defined in [Internet Date/Time Format](http://tools.ietf.org/html/rfc3339#section-5.6).
	 */
	private String endCreationDate;

	/**
	 * The offset for the search results.
	 */
	private float page;

	/**
	 * The page size for the search results.
	 */
	private float pageSize;

	/**
	 * Indicates whether the total count appears in the response. Default is `false`.
	 */
	private Boolean totalCountRequired;

	/**
	 * A flag indicating whether search is on invoices archived by merchant. true - returns archived / false returns unarchived / null returns all.
	 */
	private Boolean archived;

	/**
	 * Default Constructor
	 */
	public Search() {
	}

}

package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.Getter; import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class InvoicingMetaData  extends PayPalModel {

	/**
	 * Date when the resource was created.
	 */
	private String createdDate;

	/**
	 * Email address of the account that created the resource.
	 */
	private String createdBy;

	/**
	 * Date when the resource was cancelled.
	 */
	private String cancelledDate;

	/**
	 * Actor who cancelled the resource.
	 */
	private String cancelledBy;

	/**
	 * Date when the resource was last edited.
	 */
	private String lastUpdatedDate;

	/**
	 * Email address of the account that last edited the resource.
	 */
	private String lastUpdatedBy;

	/**
	 * Date when the resource was first sent.
	 */
	private String firstSentDate;

	/**
	 * Date when the resource was last sent.
	 */
	private String lastSentDate;

	/**
	 * Email address of the account that last sent the resource.
	 */
	private String lastSentBy;

	/**
	 * Default Constructor
	 */
	public InvoicingMetaData() {
	}
}

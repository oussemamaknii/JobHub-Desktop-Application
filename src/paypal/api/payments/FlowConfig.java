package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class FlowConfig extends PayPalModel {

	/**
	 * The type of landing page to display on the PayPal site for user checkout. Set to `Billing` to use the non-PayPal account landing page. Set to `Login` to use the PayPal account login landing page.
	 */
	private String landingPageType;

	/**
	 * The merchant site URL to display after a bank transfer payment. Valid for only the Giropay or bank transfer payment method in Germany.
	 */
	private String bankTxnPendingUrl;

	/**
	 * Defines whether buyers can complete purchases on the PayPal or merchant website.
	 */
	private String userAction;

	/**
	 * Defines the HTTP method to use to redirect the user to a return URL. A valid value is `GET` or `POST`.
	 */
	private String returnUriHttpMethod;

	/**
	 * Default Constructor
	 */
	public FlowConfig() {
	}

}

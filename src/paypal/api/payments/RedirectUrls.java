package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RedirectUrls extends PayPalModel {

	/**
	 * Url where the payer would be redirected to after approving the payment. **Required for PayPal account payments.**
	 */
	private String returnUrl;

	/**
	 * Url where the payer would be redirected to after canceling the payment. **Required for PayPal account payments.**
	 */
	private String cancelUrl;

	public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
	}

    /**
     * Default Constructor
     */
    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }

    public RedirectUrls() {
    }

	public String getReturnUrl() {
		return returnUrl;
	}
}

package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class InvoiceNumber extends PayPalModel {

	/**
	 * The next invoice number
	 */
	private String number;

	/**
	 * Default Constructor
	 */
	public InvoiceNumber() {
	}

}

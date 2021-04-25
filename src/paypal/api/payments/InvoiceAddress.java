package paypal.api.payments;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class InvoiceAddress extends BaseAddress {

	/**
	 * Phone number in E.123 format.
	 */
	private Phone phone;

	/**
	 * Default Constructor
	 */
	public InvoiceAddress() {
	}

}

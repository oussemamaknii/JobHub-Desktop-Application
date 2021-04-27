package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class NameValuePair extends PayPalModel {

	/**
	 * Key for the name value pair.  The value name types should be correlated 
	 */
	private String name;

	/**
	 * Value for the name value pair.
	 */
	private String value;

	/**
	 * Default Constructor
	 */
	public NameValuePair() {
	}

	/**
	 * Parameterized Constructor
	 */
	public NameValuePair(String name, String value) {
		this.name = name;
		this.value = value;
	}
}

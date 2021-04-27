package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Patch extends PayPalModel {

	/**
	 * The operation to perform.
	 */
	private String op;

	/**
	 * A JSON pointer that references a location in the target document where the operation is performed. A `string` value.
	 */
	private String path;

	/**
	 * New value to apply based on the operation.
	 */
	private Object value;

	/**
	 * A string containing a JSON Pointer value that references the location in the target document to move the value from.
	 */
	private String from;

	/**
	 * Default Constructor
	 */
	public Patch() {
	}

	/**
	 * Parameterized Constructor
	 */
	public Patch(String op, String path) {
		this.op = op;
		this.path = path;
	}
}

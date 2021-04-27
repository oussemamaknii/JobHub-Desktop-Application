package paypal.base.rest;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class PayPalModel {
	
	/**
	 * Returns a JSON string corresponding to object state
	 *
	 * @return JSON representation
	 */
	public String toJSON() {
		return JSONFormatter.toJSON(this);
	}

    public PayPalModel() {
    }

	@Override
	public String toString() {
		return toJSON();
	}
}

package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.Getter; import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class FmfDetails extends PayPalModel {

	/**
	 * Type of filter.
	 */
	private String filterType;

	/**
	 * Filter Identifier.
	 */
	private String filterId;

	/**
	 * Name of the filter
	 */
	private String name;

	/**
	 * Description of the filter.
	 */
	private String description;
}

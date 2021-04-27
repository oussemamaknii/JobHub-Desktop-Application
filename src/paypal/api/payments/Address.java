package paypal.api.payments;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Address extends BaseAddress {

	/**
	 * Phone number in E.123 format. 50 characters max.
	 */
	private String phone;

	/**
	 * Type of address (e.g., HOME_OR_WORK, GIFT etc).
	 */
	private String type;
}

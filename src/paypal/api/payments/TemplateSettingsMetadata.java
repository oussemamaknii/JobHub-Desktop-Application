package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TemplateSettingsMetadata extends PayPalModel {

	/**
	 * Indicates whether this field should be hidden. default is false
	 */
	private Boolean hidden;

	/**
	 * Default Constructor
	 */
	public TemplateSettingsMetadata() {
	}

}

package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class FileAttachment extends PayPalModel {

	/**
	 * Name of the file attached.
	 */
	private String name;

	/**
	 * URL of the attached file that can be downloaded.
	 */
	private String url;

	/**
	 * Default Constructor
	 */
	public FileAttachment() {
	}

}

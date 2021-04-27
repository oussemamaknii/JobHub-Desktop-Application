package paypal.api.payments;

import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.Getter; import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class WebProfileList  extends ArrayList<WebProfile> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private List<WebProfile> webProfiles = null;

	/**
	 * Default Constructor
	 */
	public WebProfileList() {
		webProfiles = new ArrayList<WebProfile>();
	}

}

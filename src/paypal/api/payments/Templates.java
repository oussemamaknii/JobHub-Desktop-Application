package paypal.api.payments;

import paypal.base.rest.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Templates extends PayPalResource {

	/**
	 * List of addresses in merchant's profile.
	 */
	private List<Address> addresses;

	/**
	 * List of emails in merchant's profile.
	 */
	private List<String> emails;

	/**
	 * List of phone numbers in merchant's profile.
	 */
	private List<Phone> phones;

	/**
	 * Array of templates.
	 */
	private List<Template> templates;

	/**
	 * HATEOS links representing all the actions over the template list returned.
	 */
	private List<Links> links;

	/**
	 * Default Constructor
	 */
	public Templates() {
	}

	/**
	 * Retrieve the details for a particular template by passing the template ID to the request URI.
	 * @deprecated Please use {@link Template#get(APIContext, String)} instead.
 	 * @param apiContext
	 *            {@link APIContext} used for the API call.
	 * @param templateId
	 *            String
	 * @return Template
	 * @throws PayPalRESTException
	 */
	public static Template get(APIContext apiContext, String templateId) throws PayPalRESTException {
		return Template.get(apiContext, templateId);
	}

	/**
	 * Retrieves the template information of the merchant.
	 * @param apiContext
	 *            {@link APIContext} used for the API call.
	 * @return Templates
	 * @throws PayPalRESTException
	 */
	public static Templates getAll(APIContext apiContext) throws PayPalRESTException {
		String resourcePath = "v1/invoicing/templates";
		String payLoad = "";
		return configureAndExecute(apiContext, HttpMethod.GET, resourcePath, payLoad, Templates.class);
	}

}

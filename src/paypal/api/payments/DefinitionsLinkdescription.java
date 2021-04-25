package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class DefinitionsLinkdescription extends PayPalModel {

	/**
	 * a URI template, as defined by RFC 6570, with the addition of the $, ( and ) characters for pre-processing
	 */
	private String href;

	/**
	 * relation to the target resource of the link
	 */
	private String rel;

	/**
	 * a title for the link
	 */
	private String title;

	/**
	 * JSON Schema describing the link target
	 */
	private DefinitionsLinkdescription targetSchema;

	/**
	 * media type (as defined by RFC 2046) describing the link target
	 */
	private String mediaType;

	/**
	 * method for requesting the target of the link (e.g. for HTTP this might be "GET" or "DELETE")
	 */
	private String method;

	/**
	 * The media type in which to submit data along with the request
	 */
	private String encType;

	/**
	 * Schema describing the data to submit along with the request
	 */
	private DefinitionsLinkdescription schema;

	/**
	 * Default Constructor
	 */
	public DefinitionsLinkdescription() {
	}

	/**
	 * Parameterized Constructor
	 */
	public DefinitionsLinkdescription(String href, String rel) {
		this.href = href;
		this.rel = rel;
	}
}

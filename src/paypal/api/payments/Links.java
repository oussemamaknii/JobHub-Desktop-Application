package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Links extends PayPalModel {
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	/**
	 * 
	 */
	private String href;

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public HyperSchema getTargetSchema() {
		return targetSchema;
	}

	public void setTargetSchema(HyperSchema targetSchema) {
		this.targetSchema = targetSchema;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getEnctype() {
		return enctype;
	}

	public void setEnctype(String enctype) {
		this.enctype = enctype;
	}

	public HyperSchema getSchema() {
		return schema;
	}

	public void setSchema(HyperSchema schema) {
		this.schema = schema;
	}

	/**
	 * 
	 */
	private String rel;

	/**
	 * 
	 */
	private HyperSchema targetSchema;

	/**
	 * 
	 */
	private String method;

	/**
	 * 
	 */
	private String enctype;

	/**
	 * 
	 */
	private HyperSchema schema;

	/**
	 * Default Constructor
	 */
	public Links() {
	}

	/**
	 * Parameterized Constructor
	 */
	public Links(String href, String rel) {
		this.href = href;
		this.rel = rel;
	}
}

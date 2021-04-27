package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class InvoiceItem extends PayPalModel {

	/**
	 * Name of the item. 200 characters max.
	 */
	private String name;

	/**
	 * Description of the item. 1000 characters max.
	 */
	private String description;

	/**
	 * Quantity of the item. Range of -10000 to 10000.
	 */
	private float quantity;

	/**
	 * Unit price of the item. Range of -1,000,000 to 1,000,000.
	 */
	private Currency unitPrice;

	/**
	 * Tax associated with the item.
	 */
	private Tax tax;

	/**
	 * The date when the item or service was provided. The date format is *yyyy*-*MM*-*dd* *z* as defined in [Internet Date/Time Format](http://tools.ietf.org/html/rfc3339#section-5.6).
	 */
	private String date;

	/**
	 * The item discount, as a percent or an amount value.
	 */
	private Cost discount;

	/**
	 * The image URL. Maximum length is 4000 characters.
	 */
	private String imageUrl;

	/**
	 * The unit of measure of the item being invoiced.
	 */
	private String unitOfMeasure;

	/**
	 * Default Constructor
	 */
	public InvoiceItem() {
	}

	/**
	 * Parameterized Constructor
	 */
	public InvoiceItem(String name, float quantity, Currency unitPrice) {
		this.name = name;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}
}

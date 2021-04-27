package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Item extends PayPalModel {

	/**
	 * Stock keeping unit corresponding (SKU) to item.
	 */
	private String sku;

	/**
	 * Item name. 127 characters max.
	 */
	private String name;

	/**
	 * Description of the item. Only supported when the `payment_method` is set to `paypal`.
	 */
	private String description;

	/**
	 * Number of a particular item. 10 characters max.
	 */
	private String quantity;

	/**
	 * Item cost. 10 characters max.
	 */
	private String price;

	/**
	 * 3-letter [currency code](https://developer.paypal.com/docs/integration/direct/rest_api_payment_country_currency_support/).
	 */
	private String currency;

	/**
	 * Tax of the item. Only supported when the `payment_method` is set to `paypal`.
	 */
	private String tax;

	/**
	 * URL linking to item information. Available to payer in transaction history.
	 */
	private String url;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Measurement getWeight() {
		return weight;
	}

	public void setWeight(Measurement weight) {
		this.weight = weight;
	}

	public Measurement getLength() {
		return length;
	}

	public void setLength(Measurement length) {
		this.length = length;
	}

	public Measurement getHeight() {
		return height;
	}

	public void setHeight(Measurement height) {
		this.height = height;
	}

	public Measurement getWidth() {
		return width;
	}

	public void setWidth(Measurement width) {
		this.width = width;
	}

	public List<NameValuePair> getSupplementaryData() {
		return supplementaryData;
	}

	public void setSupplementaryData(List<NameValuePair> supplementaryData) {
		this.supplementaryData = supplementaryData;
	}

	public List<NameValuePair> getPostbackData() {
		return postbackData;
	}

	public void setPostbackData(List<NameValuePair> postbackData) {
		this.postbackData = postbackData;
	}

	/**
	 * Category type of the item.
	 */
	private String category;

	/**
	 * Weight of the item.
	 */
	private Measurement weight;

	/**
	 * Length of the item.
	 */
	private Measurement length;

	/**
	 * Height of the item.
	 */
	private Measurement height;

	/**
	 * Width of the item.
	 */
	private Measurement width;

	/**
	 * Set of optional data used for PayPal risk determination.
	 */
	private List<NameValuePair> supplementaryData;

	/**
	 * Set of optional data used for PayPal post-transaction notifications.
	 */
	private List<NameValuePair> postbackData;

	/**
	 * Default Constructor
	 */
	public Item() {
	}

	/**
	 * Parameterized Constructor
	 */
	public Item(String name, String quantity, String price, String currency) {
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.currency = currency;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
}

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
public class Refund extends PayPalResource {

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Amount getAmount() {
		return amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public String getCaptureId() {
		return captureId;
	}

	public void setCaptureId(String captureId) {
		this.captureId = captureId;
	}

	public String getParentPayment() {
		return parentPayment;
	}

	public void setParentPayment(String parentPayment) {
		this.parentPayment = parentPayment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public List<Links> getLinks() {
		return links;
	}

	public void setLinks(List<Links> links) {
		this.links = links;
	}

	/**
	 * ID of the refund transaction. 17 characters max.
	 */
	private String id;

	/**
	 * Details including both refunded amount (to payer) and refunded fee (to payee). 10 characters max.
	 */
	private Amount amount;

	/**
	 * State of the refund.
	 */
	private String state;

	/**
	 * Reason description for the Sale transaction being refunded.
	 */
	private String reason;

	/**
	 * Your own invoice or tracking ID number. Character length and limitations: 127 single-byte alphanumeric characters.
	 */
	private String invoiceNumber;

	/**
	 * ID of the Sale transaction being refunded. 
	 */
	private String saleId;

	/**
	 * ID of the sale transaction being refunded.
	 */
	private String captureId;

	/**
	 * ID of the payment resource on which this transaction is based.
	 */
	private String parentPayment;

	/**
	 * Description of what is being refunded for.
	 */
	private String description;

	/**
	 * Time of refund as defined in [RFC 3339 Section 5.6](http://tools.ietf.org/html/rfc3339#section-5.6).
	 */
	private String createTime;

	/**
	 * Time that the resource was last updated.
	 */
	private String updateTime;

	/**
	 * The reason code for the refund state being pending
	 */
	private String reasonCode;

	/**
	 * 
	 */
	private List<Links> links;

	/**
	 * Default Constructor
	 */
	public Refund() {
	}


	/**
	 * Shows details for a refund, by ID.
	 * @deprecated Please use {@link #get(APIContext, String)} instead.
	 * @param accessToken
	 *            Access Token used for the API call.
	 * @param refundId
	 *            String
	 * @return Refund
	 * @throws PayPalRESTException
	 */
	public static Refund get(String accessToken, String refundId) throws PayPalRESTException {
		APIContext apiContext = new APIContext(accessToken);
		return get(apiContext, refundId);
	}

	/**
	 * Shows details for a refund, by ID.
	 * @param apiContext
	 *            {@link APIContext} used for the API call.
	 * @param refundId
	 *            String
	 * @return Refund
	 * @throws PayPalRESTException
	 */
	public static Refund get(APIContext apiContext, String refundId) throws PayPalRESTException {
		if (refundId == null) {
			throw new IllegalArgumentException("refundId cannot be null");
		}
		Object[] parameters = new Object[] {refundId};
		String pattern = "v1/payments/refund/{0}";
		String resourcePath = RESTUtil.formatURIPath(pattern, parameters);
		String payLoad = "";
		return configureAndExecute(apiContext, HttpMethod.GET, resourcePath, payLoad, Refund.class);
	}

}

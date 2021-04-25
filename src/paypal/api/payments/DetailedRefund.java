package paypal.api.payments;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import paypal.base.ClientCredentials;

import java.util.List;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class DetailedRefund extends Refund {
	public String getCustom() {
		return custom;
	}

	@Override
	public String getId() {
		return super.getId();
	}

	@Override
	public void setId(String id) {
		super.setId(id);
	}

	@Override
	public Amount getAmount() {
		return super.getAmount();
	}

	@Override
	public void setAmount(Amount amount) {
		super.setAmount(amount);
	}

	@Override
	public String getState() {
		return super.getState();
	}

	@Override
	public void setState(String state) {
		super.setState(state);
	}

	@Override
	public String getReason() {
		return super.getReason();
	}

	@Override
	public void setReason(String reason) {
		super.setReason(reason);
	}

	@Override
	public String getInvoiceNumber() {
		return super.getInvoiceNumber();
	}

	@Override
	public void setInvoiceNumber(String invoiceNumber) {
		super.setInvoiceNumber(invoiceNumber);
	}

	@Override
	public String getSaleId() {
		return super.getSaleId();
	}

	@Override
	public void setSaleId(String saleId) {
		super.setSaleId(saleId);
	}

	@Override
	public String getCaptureId() {
		return super.getCaptureId();
	}

	@Override
	public void setCaptureId(String captureId) {
		super.setCaptureId(captureId);
	}

	@Override
	public String getParentPayment() {
		return super.getParentPayment();
	}

	@Override
	public void setParentPayment(String parentPayment) {
		super.setParentPayment(parentPayment);
	}

	@Override
	public String getDescription() {
		return super.getDescription();
	}

	@Override
	public void setDescription(String description) {
		super.setDescription(description);
	}

	@Override
	public String getCreateTime() {
		return super.getCreateTime();
	}

	@Override
	public void setCreateTime(String createTime) {
		super.setCreateTime(createTime);
	}

	@Override
	public String getUpdateTime() {
		return super.getUpdateTime();
	}

	@Override
	public void setUpdateTime(String updateTime) {
		super.setUpdateTime(updateTime);
	}

	@Override
	public String getReasonCode() {
		return super.getReasonCode();
	}

	@Override
	public void setReasonCode(String reasonCode) {
		super.setReasonCode(reasonCode);
	}

	@Override
	public List<Links> getLinks() {
		return super.getLinks();
	}

	@Override
	public void setLinks(List<Links> links) {
		super.setLinks(links);
	}

	@Override
	public ClientCredentials getClientCredential() {
		return super.getClientCredential();
	}

	@Override
	public String toJSON() {
		return super.toJSON();
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}

	public void setCustom(String custom) {
		this.custom = custom;
	}

	public Currency getRefundToPayer() {
		return refundToPayer;
	}

	public void setRefundToPayer(Currency refundToPayer) {
		this.refundToPayer = refundToPayer;
	}

	public List<ExternalFunding> getRefundToExternalFunding() {
		return refundToExternalFunding;
	}

	public void setRefundToExternalFunding(List<ExternalFunding> refundToExternalFunding) {
		this.refundToExternalFunding = refundToExternalFunding;
	}

	public Currency getRefundFromTransactionFee() {
		return refundFromTransactionFee;
	}

	public void setRefundFromTransactionFee(Currency refundFromTransactionFee) {
		this.refundFromTransactionFee = refundFromTransactionFee;
	}

	public Currency getRefundFromReceivedAmount() {
		return refundFromReceivedAmount;
	}

	public void setRefundFromReceivedAmount(Currency refundFromReceivedAmount) {
		this.refundFromReceivedAmount = refundFromReceivedAmount;
	}

	public Currency getTotalRefundedAmount() {
		return totalRefundedAmount;
	}

	public void setTotalRefundedAmount(Currency totalRefundedAmount) {
		this.totalRefundedAmount = totalRefundedAmount;
	}

	/**
	 * free-form field for the use of clients
	 */
	private String custom;

	/**
	 * Amount refunded to payer of the original transaction, in the current Refund call
	 */
	private Currency refundToPayer;

	/**
	 * List of external funding that were refunded by the Refund call. Each external_funding unit should have a unique reference_id
	 */
	private List<ExternalFunding> refundToExternalFunding;

	/**
	 * Transaction fee refunded to original recipient of payment.
	 */
	private Currency refundFromTransactionFee;

	/**
	 * Amount subtracted from PayPal balance of the original recipient of payment, to make this refund.
	 */
	private Currency refundFromReceivedAmount;

	/**
	 * Total amount refunded so far from the original purchase. Say, for example, a buyer makes $100 purchase, the buyer was refunded $20 a week ago and is refunded $30 in this transaction. The gross refund amount is $30 (in this transaction). The total refunded amount is $50.
	 */
	private Currency totalRefundedAmount;

	/**
	 * Default Constructor
	 */
	public DetailedRefund() {
	}

}

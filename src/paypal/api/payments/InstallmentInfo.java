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
public class InstallmentInfo extends PayPalModel {

	/**
	 * Installment id.
	 */
	private String installmentId;

	/**
	 * Credit card network.
	 */
	private String network;

	/**
	 * Credit card issuer.
	 */
	private String issuer;

	/**
	 * List of available installment options and the cost associated with each one.
	 */
	private List<InstallmentOption> installmentOptions;

	/**
	 * Default Constructor
	 */
	public InstallmentInfo() {
	}

	/**
	 * Parameterized Constructor
	 */
	public InstallmentInfo(List<InstallmentOption> installmentOptions) {
		this.installmentOptions = installmentOptions;
	}
}

package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class InstallmentOption extends PayPalModel {

	/**
	 * Number of installments
	 */
	private int term;

	/**
	 * Monthly payment
	 */
	private Currency monthlyPayment;

	/**
	 * Discount amount applied to the payment, if any
	 */
	private Currency discountAmount;

	/**
	 * Discount percentage applied to the payment, if any
	 */
	private Percentage discountPercentage;

	/**
	 * Default Constructor
	 */
	public InstallmentOption() {
	}

	/**
	 * Parameterized Constructor
	 */
	public InstallmentOption(int term, Currency monthlyPayment) {
		this.term = term;
		this.monthlyPayment = monthlyPayment;
	}
}

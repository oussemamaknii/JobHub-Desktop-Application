package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.Getter; import lombok.Setter;

import java.util.List;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PaymentDefinition  extends PayPalModel {

	/**
	 * Identifier of the payment_definition. 128 characters max.
	 */
	private String id;

	/**
	 * Name of the payment definition. 128 characters max.
	 */
	private String name;

	/**
	 * Type of the payment definition. Allowed values: `TRIAL`, `REGULAR`.
	 */
	private String type;

	/**
	 * How frequently the customer should be charged.
	 */
	private String frequencyInterval;

	/**
	 * Frequency of the payment definition offered. Allowed values: `WEEK`, `DAY`, `YEAR`, `MONTH`.
	 */
	private String frequency;

	/**
	 * Number of cycles in this payment definition.
	 */
	private String cycles;

	/**
	 * Amount that will be charged at the end of each cycle for this payment definition.
	 */
	private Currency amount;

	/**
	 * Array of charge_models for this payment definition.
	 */
	private List<ChargeModels> chargeModels;

	/**
	 * Default Constructor
	 */
	public PaymentDefinition() {
	}

	/**
	 * Parameterized Constructor
	 */
	public PaymentDefinition(String name, String type, String frequencyInterval, String frequency, String cycles, Currency amount) {
		this.name = name;
		this.type = type;
		this.frequencyInterval = frequencyInterval;
		this.frequency = frequency;
		this.cycles = cycles;
		this.amount = amount;
	}
}

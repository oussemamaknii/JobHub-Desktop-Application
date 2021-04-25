package paypal.api.payments;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Transaction extends TransactionBase {

	/**
	 * Financial transactions related to a payment.
	 */
	private List<Transaction> transactions;

	/**
	 * Default Constructor
	 */
	public Transaction() {
	}

}

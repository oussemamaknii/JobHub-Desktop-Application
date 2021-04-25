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
public class Payer extends PayPalModel {

	/**
	 * Payment method being used - PayPal Wallet payment, Bank Direct Debit  or Direct Credit card.
	 */
	private String paymentMethod;

	/**
	 * Status of payer's PayPal Account.
	 */
	private String status;

	/**
	 * Type of account relationship payer has with PayPal.
	 */
	private String accountType;

	/**
	 * Duration since the payer established account relationship with PayPal in days.
	 */
	private String accountAge;

	/**
	 * List of funding instruments to fund the payment. 'OneOf' funding_instruments,funding_option_id to be used to identify the specifics of payment method passed.
	 */
	private List<FundingInstrument> fundingInstruments;

	/**
	 * Id of user selected funding option for the payment.'OneOf' funding_instruments,funding_option_id to be used to identify the specifics of payment method passed.
	 */
	private String fundingOptionId;

	/**
	 * Default funding option available for the payment 
	 */
	@Deprecated
	private FundingOption fundingOption;

	/**
	 * Instrument type pre-selected by the user outside of PayPal and passed along the payment creation. This param is used in cases such as PayPal Credit Second Button
	 */
	private String externalSelectedFundingInstrumentType;

	/**
	 * Funding option related to default funding option.
	 */
	private FundingOption relatedFundingOption;

	/**
	 * Information related to the Payer. 
	 */
	private PayerInfo payerInfo;

	/**
	 * Default Constructor
	 */
	public Payer() {
	}

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setAccountAge(String accountAge) {
        this.accountAge = accountAge;
    }

    public void setFundingInstruments(List<FundingInstrument> fundingInstruments) {
        this.fundingInstruments = fundingInstruments;
    }

    public void setFundingOptionId(String fundingOptionId) {
        this.fundingOptionId = fundingOptionId;
    }

    public void setFundingOption(FundingOption fundingOption) {
        this.fundingOption = fundingOption;
    }

    public void setExternalSelectedFundingInstrumentType(String externalSelectedFundingInstrumentType) {
        this.externalSelectedFundingInstrumentType = externalSelectedFundingInstrumentType;
    }

    public void setRelatedFundingOption(FundingOption relatedFundingOption) {
        this.relatedFundingOption = relatedFundingOption;
    }

    public void setPayerInfo(PayerInfo payerInfo) {
        this.payerInfo = payerInfo;
    }

}

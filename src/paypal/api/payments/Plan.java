package paypal.api.payments;

import paypal.base.rest.*;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.Getter; import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Plan  extends PayPalResource {

	/**
	 * Identifier of the billing plan. 128 characters max.
	 */
	private String id;

	/**
	 * Name of the billing plan. 128 characters max.
	 */
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public List<PaymentDefinition> getPaymentDefinitions() {
		return paymentDefinitions;
	}

	public void setPaymentDefinitions(List<PaymentDefinition> paymentDefinitions) {
		this.paymentDefinitions = paymentDefinitions;
	}

	public List<Terms> getTerms() {
		return terms;
	}

	public void setTerms(List<Terms> terms) {
		this.terms = terms;
	}

	public MerchantPreferences getMerchantPreferences() {
		return merchantPreferences;
	}

	public void setMerchantPreferences(MerchantPreferences merchantPreferences) {
		this.merchantPreferences = merchantPreferences;
	}

	public List<Links> getLinks() {
		return links;
	}

	public void setLinks(List<Links> links) {
		this.links = links;
	}

	/**
	 * Description of the billing plan. 128 characters max.
	 */
	private String description;

	/**
	 * Type of the billing plan. Allowed values: `FIXED`, `INFINITE`.
	 */
	private String type;

	/**
	 * Status of the billing plan. Allowed values: `CREATED`, `ACTIVE`, `INACTIVE`, and `DELETED`.
	 */
	private String state;

	/**
	 * Time when the billing plan was created. Format YYYY-MM-DDTimeTimezone, as defined in [ISO8601](http://tools.ietf.org/html/rfc3339#section-5.6).
	 */
	private String createTime;

	/**
	 * Time when this billing plan was updated. Format YYYY-MM-DDTimeTimezone, as defined in [ISO8601](http://tools.ietf.org/html/rfc3339#section-5.6).
	 */
	private String updateTime;

	/**
	 * Array of payment definitions for this billing plan.
	 */
	private List<PaymentDefinition> paymentDefinitions;

	/**
	 * Array of terms for this billing plan.
	 */
	private List<Terms> terms;

	/**
	 * Specific preferences such as: set up fee, max fail attempts, autobill amount, and others that are configured for this billing plan.
	 */
	private MerchantPreferences merchantPreferences;

	/**
	 * 
	 */
	private List<Links> links;

	/**
	 * Default Constructor
	 */
	public Plan() {
	}

	/**
	 * Parameterized Constructor
	 */
	public Plan(String name, String description, String type) {
		this.name = name;
		this.description = description;
		this.type = type;
	}

	/**
	 * Retrieve the details for a particular billing plan by passing the billing plan ID to the request URI.
	 * @deprecated Please use {@link #get(APIContext, String)} instead.
	 *
	 * @param accessToken
	 *            Access Token used for the API call.
	 * @param planId
	 *            String
	 * @return Plan
	 * @throws PayPalRESTException
	 */
	public static Plan get(String accessToken, String planId) throws PayPalRESTException {
		APIContext apiContext = new APIContext(accessToken);
		return get(apiContext, planId);
	}

	/**
	 * Retrieve the details for a particular billing plan by passing the billing plan ID to the request URI.
	 * @param apiContext
	 *            {@link APIContext} used for the API call.
	 * @param planId
	 *            String
	 * @return Plan
	 * @throws PayPalRESTException
	 */
	public static Plan get(APIContext apiContext, String planId) throws PayPalRESTException {

		if (planId == null) {
			throw new IllegalArgumentException("planId cannot be null");
		}
		Object[] parameters = new Object[] {planId};
		String pattern = "v1/payments/billing-plans/{0}";
		String resourcePath = RESTUtil.formatURIPath(pattern, parameters);
		String payLoad = "";
		return configureAndExecute(apiContext, HttpMethod.GET, resourcePath, payLoad, Plan.class);
	}


	/**
	 * Create a new billing plan by passing the details for the plan, including the plan name, description, and type, to the request URI.
	 * @deprecated Please use {@link #create(APIContext)} instead.
	 *
	 * @param accessToken
	 *            Access Token used for the API call.
	 * @return Plan
	 * @throws PayPalRESTException
	 */
	public Plan create(String accessToken) throws PayPalRESTException {
		APIContext apiContext = new APIContext(accessToken);
		return create(apiContext);
	}

	/**
	 * Create a new billing plan by passing the details for the plan, including the plan name, description, and type, to the request URI.
	 * @param apiContext
	 *            {@link APIContext} used for the API call.
	 * @return Plan
	 * @throws PayPalRESTException
	 */
	public Plan create(APIContext apiContext) throws PayPalRESTException {

		String resourcePath = "v1/payments/billing-plans";
		String payLoad = this.toJSON();
		return configureAndExecute(apiContext, HttpMethod.POST, resourcePath, payLoad, Plan.class);
	}


	/**
	 * Replace specific fields within a billing plan by passing the ID of the billing plan to the request URI. In addition, pass a patch object in the request JSON that specifies the operation to perform, field to update, and new value for each update.
	 * @deprecated Please use {@link #update(APIContext, List)} instead.
	 *
	 * @param accessToken
	 *            Access Token used for the API call.
	 * @param patchRequest
	 *            PatchRequest
	 * @throws PayPalRESTException
	 */
	public void update(String accessToken, List<Patch> patchRequest) throws PayPalRESTException {
		APIContext apiContext = new APIContext(accessToken);
		update(apiContext, patchRequest);
		return;
	}

	/**
	 * Replace specific fields within a billing plan by passing the ID of the billing plan to the request URI. In addition, pass a patch object in the request JSON that specifies the operation to perform, field to update, and new value for each update.
	 * @param apiContext
	 *            {@link APIContext} used for the API call.
	 * @param patchRequest
	 *            PatchRequest
	 * @throws PayPalRESTException
	 */
	public void update(APIContext apiContext, List<Patch> patchRequest) throws PayPalRESTException {

		if (this.getId() == null) {
			throw new IllegalArgumentException("Id cannot be null");
		}
		if (patchRequest == null) {
			throw new IllegalArgumentException("patchRequest cannot be null");
		}
		Object[] parameters = new Object[] {this.getId()};
		String pattern = "v1/payments/billing-plans/{0}";
		String resourcePath = RESTUtil.formatURIPath(pattern, parameters);
		String payLoad = JSONFormatter.toJSON(patchRequest);
		configureAndExecute(apiContext, HttpMethod.PATCH, resourcePath, payLoad, null);
		return;
	}


	/**
	 * List billing plans according to optional query string parameters specified.
	 * @deprecated Please use {@link #list(APIContext, Map)} instead.
	 *
	 * @param accessToken
	 *            Access Token used for the API call.
	 * @param containerMap
	 *            Map<String, String>
	 * @return PlanList
	 * @throws PayPalRESTException
	 */
	public static PlanList list(String accessToken, Map<String, String> containerMap) throws PayPalRESTException {
		APIContext apiContext = new APIContext(accessToken);
		return list(apiContext, containerMap);
	}

	/**
	 * List billing plans according to optional query string parameters specified.
	 * @param apiContext
	 *            {@link APIContext} used for the API call.
	 * @param containerMap
	 *            Map<String, String>
	 * @return PlanList
	 * @throws PayPalRESTException
	 */
	public static PlanList list(APIContext apiContext, Map<String, String> containerMap) throws PayPalRESTException {

		if (containerMap == null) {
			throw new IllegalArgumentException("containerMap cannot be null");
		}
		Object[] parameters = new Object[] {containerMap};
		String pattern = "v1/payments/billing-plans?page_size={0}&status={1}&page={2}&total_required={3}";
		String resourcePath = RESTUtil.formatURIPath(pattern, parameters);
		String payLoad = "";
		PlanList plans = configureAndExecute(apiContext, HttpMethod.GET, resourcePath, payLoad, PlanList.class);
		
		return plans;
	}

}

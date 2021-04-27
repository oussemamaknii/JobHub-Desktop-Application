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
public class WebProfile extends PayPalResource {

	/**
	 * The unique ID of the web experience profile.
	 */
	private String id;

	/**
	 * The web experience profile name. Unique for a specified merchant's profiles.
	 */
	private String name;

	/**
	 * Indicates whether the profile persists for three hours or permanently. Set to `false` to persist the profile permanently. Set to `true` to persist the profile for three hours.
	 */
	private Boolean temporary;

	/**
	 * Parameters for flow configuration.
	 */
	private FlowConfig flowConfig;

	/**
	 * Parameters for input fields customization.
	 */
	private InputFields inputFields;

	/**
	 * Parameters for style and presentation.
	 */
	private Presentation presentation;

	/**
	 * Default Constructor
	 */
	public WebProfile() {
	}

	/**
	 * Parameterized Constructor
	 */
	public WebProfile(String name) {
		this.name = name;
	}

	/**
	 * Creates a web experience profile. Pass the profile name and details in the JSON request body.
	 * @deprecated Please use {@link #create(APIContext)} instead.
	 * @param accessToken
	 *            Access Token used for the API call.
	 * @return CreateProfileResponse
	 * @throws PayPalRESTException
	 */
	public CreateProfileResponse create(String accessToken) throws PayPalRESTException {
		APIContext apiContext = new APIContext(accessToken);
		return create(apiContext);
	}

	/**
	 * Creates a web experience profile. Pass the profile name and details in the JSON request body.
	 * @param apiContext
	 *            {@link APIContext} used for the API call.
	 * @return CreateProfileResponse
	 * @throws PayPalRESTException
	 */
	public CreateProfileResponse create(APIContext apiContext) throws PayPalRESTException {
		String resourcePath = "v1/payment-experience/web-profiles";
		String payLoad = this.toJSON();
		return configureAndExecute(apiContext, HttpMethod.POST, resourcePath, payLoad, CreateProfileResponse.class);
	}


	/**
	 * Updates a web experience profile. Pass the ID of the profile to the request URI and pass the profile details in the JSON request body. If your request omits any profile detail fields, the operation removes the previously set values for those fields.
	 * @deprecated Please use {@link #update(APIContext)} instead.
	 * @param accessToken
	 *            Access Token used for the API call.
	 * @return
	 * @throws PayPalRESTException
	 */
	public void update(String accessToken) throws PayPalRESTException {
		APIContext apiContext = new APIContext(accessToken);
		update(apiContext);
	}

	/**
	 * Updates a web experience profile. Pass the ID of the profile to the request URI and pass the profile details in the JSON request body. If your request omits any profile detail fields, the operation removes the previously set values for those fields.
	 * @param apiContext
	 *            {@link APIContext} used for the API call.
	 * @return
	 * @throws PayPalRESTException
	 */
	public void update(APIContext apiContext) throws PayPalRESTException {
		if (this.getId() == null) {
			throw new IllegalArgumentException("Id cannot be null");
		}
		Object[] parameters = new Object[] {this.getId()};
		String pattern = "v1/payment-experience/web-profiles/{0}";
		String resourcePath = RESTUtil.formatURIPath(pattern, parameters);
		String payLoad = this.toJSON();
		configureAndExecute(apiContext, HttpMethod.PUT, resourcePath, payLoad, null);
		return;
	}

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

	public Boolean getTemporary() {
		return temporary;
	}

	public void setTemporary(Boolean temporary) {
		this.temporary = temporary;
	}

	public FlowConfig getFlowConfig() {
		return flowConfig;
	}

	public void setFlowConfig(FlowConfig flowConfig) {
		this.flowConfig = flowConfig;
	}

	public InputFields getInputFields() {
		return inputFields;
	}

	public void setInputFields(InputFields inputFields) {
		this.inputFields = inputFields;
	}

	public Presentation getPresentation() {
		return presentation;
	}

	public void setPresentation(Presentation presentation) {
		this.presentation = presentation;
	}

	/**
	 * Partially-updates a web experience profile. Pass the profile ID to the request URI. Pass a patch object with the operation, path of the profile location to update, and, if needed, a new value to complete the operation in the JSON request body.
	 * @deprecated Please use {@link #partialUpdate(APIContext, PatchRequest)} instead.
	 *
	 * @param accessToken
	 *            Access Token used for the API call.
	 * @param patchRequest
	 *            PatchRequest
     * @return
	 * @throws PayPalRESTException
	 */
	public void partialUpdate(String accessToken, PatchRequest patchRequest) throws PayPalRESTException {
		APIContext apiContext = new APIContext(accessToken);
		partialUpdate(apiContext, patchRequest);
	}

	/**
	 * Partially-updates a web experience profile. Pass the profile ID to the request URI. Pass a patch object with the operation, path of the profile location to update, and, if needed, a new value to complete the operation in the JSON request body.
	 * @param apiContext
	 *            {@link APIContext} used for the API call.
	 * @param patchRequest
	 *            PatchRequest
     * @return
	 * @throws PayPalRESTException
	 */
	public void partialUpdate(APIContext apiContext, PatchRequest patchRequest) throws PayPalRESTException {
		if (this.getId() == null) {
			throw new IllegalArgumentException("Id cannot be null");
		}
		if (patchRequest == null) {
			throw new IllegalArgumentException("patchRequest cannot be null");
		}
		Object[] parameters = new Object[] {this.getId()};
		String pattern = "v1/payment-experience/web-profiles/{0}";
		String resourcePath = RESTUtil.formatURIPath(pattern, parameters);
		String payLoad = patchRequest.toJSON();
		configureAndExecute(apiContext, HttpMethod.PATCH, resourcePath, payLoad, null);
		return;
	}


	/**
	 * Shows details for a web experience profile, by ID.
	 * @deprecated Please use {@link #get(APIContext, String)} instead.
	 * @param accessToken
	 *            Access Token used for the API call.
	 * @param profileId
	 *            String
	 * @return WebProfile
	 * @throws PayPalRESTException
	 */
	public static WebProfile get(String accessToken, String profileId) throws PayPalRESTException {
		APIContext apiContext = new APIContext(accessToken);
		return get(apiContext, profileId);
	}

	/**
	 * Shows details for a web experience profile, by ID.
	 * @param apiContext
	 *            {@link APIContext} used for the API call.
	 * @param profileId
	 *            String
	 * @return WebProfile
	 * @throws PayPalRESTException
	 */
	public static WebProfile get(APIContext apiContext, String profileId) throws PayPalRESTException {
		if (profileId == null) {
			throw new IllegalArgumentException("profileId cannot be null");
		}
		Object[] parameters = new Object[] {profileId};
		String pattern = "v1/payment-experience/web-profiles/{0}";
		String resourcePath = RESTUtil.formatURIPath(pattern, parameters);
		String payLoad = "";
		return configureAndExecute(apiContext, HttpMethod.GET, resourcePath, payLoad, WebProfile.class);
	}


	/**
	 * Lists all web experience profiles for a merchant or subject.
	 * @deprecated Please use {@link #getList(APIContext)} instead.
	 * @param accessToken
	 *            Access Token used for the API call.
	 * @return WebProfileList
	 * @throws PayPalRESTException
	 */
	public static List<WebProfile> getList(String accessToken) throws PayPalRESTException {
		APIContext apiContext = new APIContext(accessToken);
		return getList(apiContext);
	}

	/**
	 * Lists all web experience profiles for a merchant or subject.
	 * @param apiContext
	 *            {@link APIContext} used for the API call.
	 * @return WebProfileList
	 * @throws PayPalRESTException
	 */
	public static List<WebProfile> getList(APIContext apiContext) throws PayPalRESTException {
		String resourcePath = "v1/payment-experience/web-profiles";
		String payLoad = "";
		return configureAndExecute(apiContext, HttpMethod.GET, resourcePath, payLoad, WebProfileList.class);
	}


	/**
	 * Deletes a web experience profile, by ID.
	 * @deprecated Please use {@link #delete(APIContext)} instead.
	 * @param accessToken
	 *            Access Token used for the API call.
	 * @return
	 * @throws PayPalRESTException
	 */
	public void delete(String accessToken) throws PayPalRESTException {
		APIContext apiContext = new APIContext(accessToken);
		delete(apiContext);
	}

	/**
	 * Deletes a web experience profile, by ID.
	 * @param apiContext
	 *            {@link APIContext} used for the API call.
	 * @return
	 * @throws PayPalRESTException
	 */
	public void delete(APIContext apiContext) throws PayPalRESTException {
		if (this.getId() == null) {
			throw new IllegalArgumentException("Id cannot be null");
		}
		Object[] parameters = new Object[] {this.getId()};
		String pattern = "v1/payment-experience/web-profiles/{0}";
		String resourcePath = RESTUtil.formatURIPath(pattern, parameters);
		String payLoad = "";
		configureAndExecute(apiContext, HttpMethod.DELETE, resourcePath, payLoad, null);
	}
}

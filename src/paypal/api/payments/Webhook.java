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
public class Webhook extends PayPalResource {

    /**
     * The ID of the webhook.
     */
    private String id;

    /**
     * The URL that is configured to listen on `localhost` for incoming `POST` notification messages that contain event information.
     */
    private String url;

    /**
     * A list of up to ten events to which to subscribe your webhook. To subscribe to all events including new events as they are added, specify the asterisk (`*`) wildcard. To replace the `event_types` array, specify the `*` wildcard. To see all supported events, [list available events](#available-event-type.list).
     */
    private List<EventType> eventTypes;

    /**
     * The HATEOAS links related to this call.
     */
    private List<Links> links;

    /**
     * Default Constructor
     */
    public Webhook() {
    }

    /**
     * Parameterized Constructor
     */
    public Webhook(String url, List<EventType> eventTypes) {
        this.url = url;
        this.eventTypes = eventTypes;
    }

    /**
     * Subscribes your webhook listener to events. A successful call returns a [`webhook`](/docs/api/webhooks/#definition-webhook) object, which includes the webhook ID for later use.
     *
     * @param accessToken Access Token used for the API call.
     * @param webhook     Webhook request
     * @return Webhook
     * @throws PayPalRESTException
     * @deprecated Please use {@link #create(APIContext, Webhook)} instead.
     */
    @Deprecated
    public Webhook create(String accessToken, Webhook webhook) throws PayPalRESTException {
        APIContext apiContext = new APIContext(accessToken);
        return create(apiContext, webhook);
    }

    /**
     * Subscribes your webhook listener to events. A successful call returns a [`webhook`](/docs/api/webhooks/#definition-webhook) object, which includes the webhook ID for later use.
     *
     * @param apiContext {@link APIContext} used for the API call.
     * @param webhook    Webhook request
     * @return Webhook
     * @throws PayPalRESTException
     */
    public Webhook create(APIContext apiContext, Webhook webhook) throws PayPalRESTException {
        if (webhook == null) {
            throw new IllegalArgumentException("webhook cannot be null");
        }

        Object[] parameters = new Object[]{};
        String pattern = "v1/notifications/webhooks";
        String resourcePath = RESTUtil.formatURIPath(pattern, parameters);
        String payLoad = webhook.toJSON();
        return configureAndExecute(apiContext, HttpMethod.POST, resourcePath, payLoad, Webhook.class);
    }


    /**
     * Shows details for a webhook, by ID.
     *
     * @param accessToken Access Token used for the API call.
     * @param webhookId   Identifier of the webhook
     * @return Webhook
     * @throws PayPalRESTException
     * @deprecated Please use {@link #get(APIContext, String)} instead.
     */
    @Deprecated
    public Webhook get(String accessToken, String webhookId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(accessToken);
        return get(apiContext, webhookId);
    }

    /**
     * Shows details for a webhook, by ID.
     *
     * @param apiContext {@link APIContext} used for the API call.
     * @param webhookId  Identifier of the webhook
     * @return Webhook
     * @throws PayPalRESTException
     */
    public Webhook get(APIContext apiContext, String webhookId) throws PayPalRESTException {
        if (webhookId == null) {
            throw new IllegalArgumentException("webhookId cannot be null");
        }

        Object[] parameters = new Object[]{webhookId};
        String pattern = "v1/notifications/webhooks/{0}";
        String resourcePath = RESTUtil.formatURIPath(pattern, parameters);
        String payLoad = "";
        return configureAndExecute(apiContext, HttpMethod.GET, resourcePath, payLoad, Webhook.class);
    }

    /**
     * Replaces webhook fields with new values. Pass a `json_patch` object with `replace` operation and `path`, which is `/url` for a URL or `/event_types` for events. The `value` is either the URL or a list of events.
     *
     * @param accessToken  Access Token used for the API call.
     * @param webhookId    Identifier of the webhook
     * @param patchRequest patchRequest
     * @return Webhook
     * @throws PayPalRESTException
     * @deprecated Please use {@link #update(APIContext, String, String)} instead.
     */
    @Deprecated
    public Webhook update(String accessToken, String webhookId, String patchRequest) throws PayPalRESTException {
        APIContext apiContext = new APIContext(accessToken);
        return update(apiContext, webhookId, patchRequest);
    }

    /**
     * Replaces webhook fields with new values. Pass a `json_patch` object with `replace` operation and `path`, which is `/url` for a URL or `/event_types` for events. The `value` is either the URL or a list of events.
     *
     * @param apiContext {@link APIContext} used for the API call.
     * @return Webhook
     * @throws PayPalRESTException
     */
    public Webhook update(APIContext apiContext, String webhookId, String patchRequest) throws PayPalRESTException {
        if (webhookId == null) {
            throw new IllegalArgumentException("webhookId cannot be null");
        }
        if (patchRequest == null) {
            throw new IllegalArgumentException("patchRequest cannot be null");
        }
        Object[] parameters = new Object[]{webhookId};
        String pattern = "v1/notifications/webhooks/{0}";
        String resourcePath = RESTUtil.formatURIPath(pattern, parameters);
        String payLoad = patchRequest;
        return configureAndExecute(apiContext, HttpMethod.PATCH, resourcePath, payLoad, Webhook.class);
    }

    /**
     * Deletes a webhook, by ID.
     *
     * @param accessToken Access Token used for the API call.
     * @param webhookId   Identifier of the webhook
     * @throws PayPalRESTException
     * @deprecated Please use {@link #delete(APIContext, String)} instead.
     */
    @Deprecated
    public void delete(String accessToken, String webhookId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(accessToken);
        delete(apiContext, webhookId);
    }

    /**
     * Deletes a webhook, by ID.
     *
     * @param apiContext {@link APIContext} used for the API call.
     * @param webhookId  Identifier of the webhook
     * @throws PayPalRESTException
     */
    public void delete(APIContext apiContext, String webhookId) throws PayPalRESTException {
        if (webhookId == null) {
            throw new IllegalArgumentException("webhookId cannot be null");
        }

        Object[] parameters = new Object[]{webhookId};
        String pattern = "v1/notifications/webhooks/{0}";
        String resourcePath = RESTUtil.formatURIPath(pattern, parameters);
        String payLoad = "";
        configureAndExecute(apiContext, HttpMethod.DELETE, resourcePath, payLoad, null);
    }
}

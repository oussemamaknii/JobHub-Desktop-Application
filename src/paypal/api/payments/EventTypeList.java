package paypal.api.payments;

import paypal.base.rest.PayPalModel;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.Getter; import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class EventTypeList  extends PayPalModel {

	/**
	 * A list of Webhooks event-types
	 */
	private List<EventType> eventTypes;

	/**
	 * Default Constructor
	 */
	public EventTypeList() {
		eventTypes = new ArrayList<EventType>();
	}
}

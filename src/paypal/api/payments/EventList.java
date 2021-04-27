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
public class EventList  extends PayPalModel {

	/**
	 * A list of Webhooks event resources
	 */
	private List<Event> events;

	/**
	 * Number of items returned in each range of results. Note that the last results range could have fewer items than the requested number of items.
	 */
	private int count;

	/**
	 * 
	 */
	private List<Links> links;

	/**
	 * Default Constructor
	 */
	public EventList() {
		events = new ArrayList<Event>();
	}

}


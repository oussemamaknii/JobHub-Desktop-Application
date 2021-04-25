package paypal.base;

/**
 * APICallPreHandlerFactory factory for returning implementations if
 * {@link APICallPreHandler}
 *
 */
public interface APICallPreHandlerFactory {

	/**
	 * Creates an instance of {@link APICallPreHandler}
	 * 
	 * @return APICallPreHandler
	 */
	APICallPreHandler createAPICallPreHandler();

}

package paypal.base.sdk.info;

import paypal.base.SDKVersion;

/**
 * Implementation of SDKVersion
 */
public class SDKVersionImpl implements SDKVersion {

    /**
	 * SDK ID used in User-Agent HTTP header
	 */
	private static final String SDK_ID = "PayPal-Java-SDK";
	
	/**
	 * SDK Version used in User-Agent HTTP header
	 */
	private static final String SDK_VERSION = "1.14.0";
	
	public String getSDKId() {
		return SDK_ID;	
	}
	
	public String getSDKVersion() {
		return SDK_VERSION;
	}
	
}

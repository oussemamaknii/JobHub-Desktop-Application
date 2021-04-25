package paypal.base.util;

import paypal.base.Constants;

import java.util.HashMap;
import java.util.Map;

public class UserAgentHeader {

	/**
	 * Product Id
	 */
	private String productId;

	/**
	 * Product Version
	 */
	private String productVersion;

	/**
	 * UserAgentHeader
	 * 
	 * @param productId
	 *            Product Id: Defaults to empty string if null or empty
	 * @param productVersion
	 *            Product Version : Defaults to empty string if null or empty
	 */
	public UserAgentHeader(String productId, String productVersion) {
		super();
		this.productId = productId != null && productId.trim().length() > 0 ? productId
				: "";
		this.productVersion = productVersion != null
				&& productVersion.trim().length() > 0 ? productVersion : "";
	}

	/**
	 * Java Version and bit header computed during construction
	 */
	private static final String JAVAHEADER;

	/**
	 * OS Version and bit header computed during construction
	 */
	private static final String OSHEADER;

	static {

		// Java Version computed statically
		StringBuilder javaVersion = new StringBuilder();
		if (System.getProperty("java.version") != null
				&& System.getProperty("java.version").length() > 0) {
			javaVersion.append("v=")
					.append(System.getProperty("java.version"));
		}
		if (System.getProperty("java.vendor") != null
				&& System.getProperty("java.vendor").length() > 0) {
			javaVersion.append("; vendor=" + System.getProperty("java.vendor"));
		}
		if (System.getProperty("java.vm.name") != null
				&& System.getProperty("java.vm.name").length() > 0) {
			javaVersion.append("; bit=");
			if (System.getProperty("java.vm.name").contains("Client")) {
				javaVersion.append("32");
			} else {
				javaVersion.append("64");
			}
		}
		JAVAHEADER = javaVersion.toString();

		// OS Version Header
		StringBuilder osVersion = new StringBuilder();
		if (System.getProperty("os.name") != null
				&& System.getProperty("os.name").length() > 0) {
			osVersion.append("os=");
			osVersion.append(System.getProperty("os.name").replace(' ', '_'));
		} else {
			osVersion.append("os=");
		}
		if (System.getProperty("os.version") != null
				&& System.getProperty("os.version").length() > 0) {
			osVersion.append(" "
					+ System.getProperty("os.version").replace(' ', '_'));
		}
		OSHEADER = osVersion.toString();
	}

	public Map<String, String> getHeader() {
		Map<String, String> userAgentMap = new HashMap<String, String>();
		userAgentMap.put(Constants.USER_AGENT_HEADER, formUserAgentHeader());
		return userAgentMap;
	}

	/**
	 * Returns User-Agent header
	 * 
	 * @return
	 */
	private String formUserAgentHeader() {
		String header = null;
		StringBuilder stringBuilder = new StringBuilder("PayPalSDK/"
				+ productId + " " + productVersion + " ");
		stringBuilder.append("(").append(JAVAHEADER);
		String osVersion = OSHEADER;
		if (osVersion.length() > 0) {
			stringBuilder.append("; ").append(osVersion);
		}
		stringBuilder.append(")");
		header = stringBuilder.toString();
		return header;
	}

}

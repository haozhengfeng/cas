package org.haozf.common.context;

import org.haozf.oauth2.core.domain.oauth.AccessToken;
import org.haozf.oauth2.core.domain.oauth.ClientDetails;

public class ApiContext {
	public static ThreadLocal<AccessToken> accessToken = new ThreadLocal<AccessToken>();
	public static ThreadLocal<ClientDetails> clientDetails = new ThreadLocal<ClientDetails>();

}

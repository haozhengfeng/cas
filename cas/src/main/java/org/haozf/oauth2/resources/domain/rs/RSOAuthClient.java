package org.haozf.oauth2.resources.domain.rs;

import org.apache.oltu.oauth2.rsfilter.OAuthClient;
import org.haozf.oauth2.core.domain.oauth.ClientDetails;

/**
 * 2015/10/7
 * <p/>
 * Wrapper ClientDetails
 * implement OAuthClient
 *
 * @author Shengzhao Li
 * @see com.monkeyk.os.domain.oauth.ClientDetails
 */
public class RSOAuthClient implements OAuthClient {

    private ClientDetails clientDetails;

    public RSOAuthClient(ClientDetails clientDetails) {
        this.clientDetails = clientDetails;
    }

    @Override
    public String getClientId() {
        return clientDetails.getClientId();
    }

    public ClientDetails clientDetails() {
        return clientDetails;
    }
}

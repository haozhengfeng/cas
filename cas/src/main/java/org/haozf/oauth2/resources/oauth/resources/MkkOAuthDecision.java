package org.haozf.oauth2.resources.oauth.resources;

import java.security.Principal;

import org.apache.oltu.oauth2.rsfilter.OAuthClient;
import org.apache.oltu.oauth2.rsfilter.OAuthDecision;
import org.haozf.oauth2.resources.domain.rs.RSOAuthClient;

/**
 * 2015/7/8
 *
 * @author Shengzhao Li
 */
public class MkkOAuthDecision implements OAuthDecision {


    private boolean authorized;

    private Principal principal;

    private RSOAuthClient oAuthClient;

    public MkkOAuthDecision() {
    }

    @Override
    public boolean isAuthorized() {
        return authorized;
    }

    @Override
    public Principal getPrincipal() {
        return principal;
    }

    @Override
    public OAuthClient getOAuthClient() {
        return oAuthClient;
    }

    public MkkOAuthDecision setPrincipal(Principal principal) {
        this.principal = principal;
        return this;
    }

    public MkkOAuthDecision setOAuthClient(RSOAuthClient oAuthClient) {
        this.oAuthClient = oAuthClient;
        return this;
    }

    public MkkOAuthDecision setAuthorized(boolean authorized) {
        this.authorized = authorized;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "authorized=" + authorized +
                ", principal=" + principal +
                ", oAuthClient=" + oAuthClient +
                '}';
    }
}

package org.haozf.oauth2.core.domain.oauth;

import org.haozf.oauth2.core.domain.AbstractDomain;

/**
 * 15-6-17
 *
 * @author Shengzhao Li
 */
public class OauthCode extends AbstractDomain {

    private static final long serialVersionUID = 7861853986708936572L;
    private String code;

    private String username;

    private String clientId;

    public OauthCode() {
    }


    public String code() {
        return code;
    }

    public OauthCode code(String code) {
        this.code = code;
        return this;
    }

    public String username() {
        return username;
    }

    public OauthCode username(String username) {
        this.username = username;
        return this;
    }

    public String clientId() {
        return clientId;
    }

    public OauthCode clientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

}

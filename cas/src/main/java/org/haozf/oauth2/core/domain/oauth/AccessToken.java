package org.haozf.oauth2.core.domain.oauth;

import org.haozf.common.util.DateUtils;
import org.haozf.oauth2.core.domain.AbstractDomain;

/**
 * 15-6-20
 *
 * @author Shengzhao Li
 */
public class AccessToken extends AbstractDomain {


    public static final String BEARER_TYPE = "Bearer";

    // default 30 days.
    public final static int REFRESH_TOKEN_VALIDITY_SECONDS = 60 * 60 * 24 * 30;
    // default 12 hours.
    public final static int ACCESS_TOKEN_VALIDITY_SECONDS = 60 * 60 * 12;
    private static final long serialVersionUID = 7336709167855003668L;


    protected static long THOUSAND = 1000L;


    private String tokenId;

    private String username;

    private String clientId;

    private String authenticationId;

    private String refreshToken;

    private String tokenType = BEARER_TYPE;

    private int tokenExpiredSeconds = ACCESS_TOKEN_VALIDITY_SECONDS;

    private int refreshTokenExpiredSeconds = REFRESH_TOKEN_VALIDITY_SECONDS;


    public AccessToken() {
    }


    public boolean tokenExpired() {
        final long time = createTime.getTime() + (this.tokenExpiredSeconds * THOUSAND);
        return time < DateUtils.now().getTime();
    }


    public boolean refreshTokenExpired() {
        final long time = createTime.getTime() + (this.refreshTokenExpiredSeconds * THOUSAND);
        return time < DateUtils.now().getTime();
    }


    public long currentTokenExpiredSeconds() {
        if (tokenExpired()) {
            return -1;
        }
        final long time = createTime.getTime() + (this.tokenExpiredSeconds * THOUSAND);
        return (time - DateUtils.now().getTime()) / THOUSAND;
    }

    public AccessToken updateByClientDetails(ClientDetails clientDetails) {
        this.clientId = clientDetails.getClientId();

        final Integer accessTokenValidity = clientDetails.accessTokenValidity();
        if (accessTokenValidity != null && accessTokenValidity > 0) {
            this.tokenExpiredSeconds = accessTokenValidity;
        }

        final Integer refreshTokenValidity = clientDetails.refreshTokenValidity();
        if (refreshTokenValidity != null && refreshTokenValidity > 0) {
            this.refreshTokenExpiredSeconds = refreshTokenValidity;
        }

        return this;
    }

    public String tokenId() {
        return tokenId;
    }

    public AccessToken tokenId(String tokenId) {
        this.tokenId = tokenId;
        return this;
    }

    public String username() {
        return username;
    }

    public AccessToken username(String username) {
        this.username = username;
        return this;
    }

    public String clientId() {
        return clientId;
    }

    public AccessToken clientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public String refreshToken() {
        return refreshToken;
    }

    public AccessToken refreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public String tokenType() {
        return tokenType;
    }

    public AccessToken tokenType(String tokenType) {
        this.tokenType = tokenType;
        return this;
    }

    public int tokenExpiredSeconds() {
        return tokenExpiredSeconds;
    }

    public AccessToken tokenExpiredSeconds(int tokenExpiredSeconds) {
        this.tokenExpiredSeconds = tokenExpiredSeconds;
        return this;
    }

    public int refreshTokenExpiredSeconds() {
        return refreshTokenExpiredSeconds;
    }

    public AccessToken refreshTokenExpiredSeconds(int refreshTokenExpiredSeconds) {
        this.refreshTokenExpiredSeconds = refreshTokenExpiredSeconds;
        return this;
    }


    public String authenticationId() {
        return authenticationId;
    }

    public AccessToken authenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "tokenId='" + tokenId + '\'' +
                ", username='" + username + '\'' +
                ", clientId='" + clientId + '\'' +
                ", authenticationId='" + authenticationId + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", tokenExpiredSeconds=" + tokenExpiredSeconds +
                ", refreshTokenExpiredSeconds=" + refreshTokenExpiredSeconds +
                '}';
    }

    /**
     * Clone
     * Exclude token, refresh_token, authenticationId, expired
     *
     * @return New AccessToken instance
     */
    public AccessToken cloneMe() {
        return new AccessToken()
                .username(username)
                .clientId(clientId)
                .tokenType(tokenType);
    }
}

/*
 * Copyright (c) 2013 Andaily Information Technology Co. Ltd
 * www.andaily.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Andaily Information Technology Co. Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Andaily Information Technology Co. Ltd.
 */
package org.haozf.oauth2.authz.oauth.token;

import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.haozf.oauth2.authz.oauth.OAuthHandler;
import org.haozf.oauth2.authz.oauth.validator.AbstractClientDetailsValidator;
import org.haozf.oauth2.authz.web.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author haozhengfeng
 *
 */
public abstract class AbstractOAuthTokenHandler extends OAuthHandler implements OAuthTokenHandler {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractOAuthTokenHandler.class);


    protected OAuthTokenRequest tokenRequest;
    protected HttpServletResponse response;

    @Override
    public final void handle(OAuthTokenRequest tokenRequest, HttpServletResponse response) throws OAuthProblemException, OAuthSystemException {
        this.tokenRequest = tokenRequest;
        this.response = response;

        //validate
        if (validateFailed()) {
            return;
        }


        handleAfterValidation();
    }


    protected boolean validateFailed() throws OAuthSystemException {
        AbstractClientDetailsValidator validator = getValidator();
        LOG.debug("Use [{}] validate client: {}", validator, tokenRequest.getClientId());

        final OAuthResponse oAuthResponse = validator.validate();
        return checkAndResponseValidateFailed(oAuthResponse);
    }

    protected boolean checkAndResponseValidateFailed(OAuthResponse oAuthResponse) {
        if (oAuthResponse != null) {
            LOG.debug("Validate OAuthAuthzRequest(client_id={}) failed", tokenRequest.getClientId());
            WebUtils.writeOAuthJsonResponse(response, oAuthResponse);
            return true;
        }
        return false;
    }

    protected abstract AbstractClientDetailsValidator getValidator();


    protected String clientId() {
        return tokenRequest.getClientId();
    }

    protected abstract void handleAfterValidation() throws OAuthProblemException, OAuthSystemException;


}

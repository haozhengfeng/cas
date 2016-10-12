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
package org.haozf.oauth2.resources.service;

import org.haozf.oauth2.core.domain.oauth.AccessToken;
import org.haozf.oauth2.core.domain.oauth.ClientDetails;

/**
 * 2015/7/8
 *
 * @author Shengzhao Li
 */

public interface OAuthRSService {

    AccessToken loadAccessTokenByTokenId(String tokenId);

    ClientDetails loadClientDetails(String clientId, String resourceIds);

}
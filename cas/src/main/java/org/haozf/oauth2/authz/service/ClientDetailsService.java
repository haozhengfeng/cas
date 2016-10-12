package org.haozf.oauth2.authz.service;

import org.haozf.oauth2.authz.service.dto.ClientDetailsDto;
import org.haozf.oauth2.authz.service.dto.ClientDetailsFormDto;
import org.haozf.oauth2.authz.service.dto.ClientDetailsListDto;

/**
 * 2016/6/8
 *
 * @author Shengzhao Li
 */

public interface ClientDetailsService {

    ClientDetailsListDto loadClientDetailsListDto(String clientId);

    ClientDetailsFormDto loadClientDetailsFormDto();

    String saveClientDetails(ClientDetailsFormDto formDto);

    ClientDetailsDto loadClientDetailsDto(String clientId);
}
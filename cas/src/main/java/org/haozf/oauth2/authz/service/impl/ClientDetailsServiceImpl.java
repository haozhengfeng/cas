package org.haozf.oauth2.authz.service.impl;

import java.util.List;

import org.haozf.oauth2.authz.domain.oauth.OauthRepository;
import org.haozf.oauth2.authz.domain.users.UsersAuthzRepository;
import org.haozf.oauth2.authz.service.ClientDetailsService;
import org.haozf.oauth2.authz.service.business.ClientDetailsFormSaver;
import org.haozf.oauth2.authz.service.dto.ClientDetailsDto;
import org.haozf.oauth2.authz.service.dto.ClientDetailsFormDto;
import org.haozf.oauth2.authz.service.dto.ClientDetailsListDto;
import org.haozf.oauth2.core.domain.oauth.ClientDetails;
import org.haozf.oauth2.core.domain.users.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 2016/6/8
 *
 * @author Shengzhao Li
 */
@Service("clientDetailsService")
public class ClientDetailsServiceImpl implements ClientDetailsService {


    @Autowired
    private OauthRepository oauthRepository;
    @Autowired
    private UsersAuthzRepository usersAuthzRepository;


    @Override
    public ClientDetailsListDto loadClientDetailsListDto(String clientId) {
        List<ClientDetails> clientDetailsList = oauthRepository.findClientDetailsListByClientId(clientId);
        return new ClientDetailsListDto(clientId, clientDetailsList);
    }

    @Override
    public ClientDetailsFormDto loadClientDetailsFormDto() {
        List<Roles> rolesList = usersAuthzRepository.findAvailableRolesList();
        return new ClientDetailsFormDto(rolesList);
    }

    @Override
    public String saveClientDetails(ClientDetailsFormDto formDto) {
        ClientDetailsFormSaver saver = new ClientDetailsFormSaver(formDto);
        return saver.save();
    }

    @Override
    public ClientDetailsDto loadClientDetailsDto(String clientId) {
        ClientDetails clientDetails = oauthRepository.findClientDetails(clientId);
        return new ClientDetailsDto(clientDetails);
    }
}

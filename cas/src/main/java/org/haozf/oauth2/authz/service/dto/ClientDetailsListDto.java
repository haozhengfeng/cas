package org.haozf.oauth2.authz.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.haozf.oauth2.core.domain.oauth.ClientDetails;

/**
 * 2016/6/8
 *
 * @author Shengzhao Li
 */
public class ClientDetailsListDto implements Serializable {
    private static final long serialVersionUID = -6327364441565670231L;


    private String clientId;

    private List<ClientDetailsDto> clientDetailsDtos = new ArrayList<>();


    public ClientDetailsListDto() {
    }

    public ClientDetailsListDto(String clientId, List<ClientDetails> list) {
        this.clientId = clientId;
        this.clientDetailsDtos = ClientDetailsDto.toDtos(list);
    }

    public int getSize() {
        return clientDetailsDtos.size();
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public List<ClientDetailsDto> getClientDetailsDtos() {
        return clientDetailsDtos;
    }

    public void setClientDetailsDtos(List<ClientDetailsDto> clientDetailsDtos) {
        this.clientDetailsDtos = clientDetailsDtos;
    }
}

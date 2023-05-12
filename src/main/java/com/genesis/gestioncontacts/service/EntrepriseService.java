package com.genesis.gestioncontacts.service;

import com.genesis.gestioncontacts.dto.EntrepriseDto;
import com.genesis.gestioncontacts.dto.request.EntrepriseRequestDto;

import java.util.List;

public interface EntrepriseService {
    List<EntrepriseDto> getAllEnterprises();

    EntrepriseDto addNewEnterprise(EntrepriseRequestDto entrepriseRequestDto);

    EntrepriseDto editEnterprise(Long id, EntrepriseRequestDto entrepriseRequestDto);

    EntrepriseDto getEnterpriseByTva(Integer tva);
}

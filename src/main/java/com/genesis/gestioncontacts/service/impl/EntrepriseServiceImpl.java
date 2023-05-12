package com.genesis.gestioncontacts.service.impl;

import com.genesis.gestioncontacts.dto.EntrepriseDto;
import com.genesis.gestioncontacts.dto.mapper.EntrepriseDtoMapper;
import com.genesis.gestioncontacts.dto.request.EntrepriseRequestDto;
import com.genesis.gestioncontacts.exception.EntrepriseException;
import com.genesis.gestioncontacts.model.Entreprise;
import com.genesis.gestioncontacts.repository.EntrepriseRepository;
import com.genesis.gestioncontacts.service.EntrepriseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntrepriseServiceImpl implements EntrepriseService {

    private final EntrepriseRepository entrepriseRepository;
    private final EntrepriseDtoMapper mapper;

    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository, EntrepriseDtoMapper mapper) {
        this.entrepriseRepository = entrepriseRepository;
        this.mapper = mapper;
    }

    @Override
    public List<EntrepriseDto> getAllEnterprises() {

        return entrepriseRepository.findAll().stream().map(mapper).collect(Collectors.toList());
    }



    @Override
    public EntrepriseDto addNewEnterprise(EntrepriseRequestDto entrepriseRequestDto) {
        Optional<Entreprise> entrepriseOpt = entrepriseRepository.findByTva(entrepriseRequestDto.getTva());
        if(entrepriseOpt.isPresent())
            throw new EntrepriseException.TowOrEnterpriseHaveTheSameTva("il existe déjà une entreprise avec ce tva");
        Entreprise entreprise = new Entreprise(entrepriseRequestDto.getTva(), entrepriseRequestDto.getAdresse());
        Entreprise save = entrepriseRepository.save(entreprise);
        return Optional.of(save).map(mapper).get();
    }

    @Override
    public EntrepriseDto editEnterprise(Long id, EntrepriseRequestDto entrepriseRequestDto) {
        Optional<Entreprise> optionalEnterprise = entrepriseRepository.findById(id);
        Entreprise entreprise = optionalEnterprise.orElseThrow(() -> new EntrepriseException.EntrepriseNotFound("Entreprise non trouvée"));

        Optional<Entreprise> byTva = entrepriseRepository.findByTva(entrepriseRequestDto.getTva());
        if(byTva.isPresent() && !byTva.get().getId().equals(id))
            throw new EntrepriseException.TowOrEnterpriseHaveTheSameTva("il existe déjà une entreprise avec ce tva");

        entreprise.setTva(entrepriseRequestDto.getTva());
        entreprise.setAdresse(entrepriseRequestDto.getAdresse());
        Entreprise save = entrepriseRepository.save(entreprise);
        return Optional.of(save).map(mapper).get();

    }

    @Override
    public EntrepriseDto getEnterpriseByTva(Integer tva) {
        Optional<Entreprise> optionalEnterprise = entrepriseRepository.findByTva(tva);
        EntrepriseDto entrepriseDto = optionalEnterprise.map(mapper).orElseThrow(() -> new EntrepriseException.EntrepriseNotFound("Entreprise non trouvée"));
        return  entrepriseDto;
    }
}

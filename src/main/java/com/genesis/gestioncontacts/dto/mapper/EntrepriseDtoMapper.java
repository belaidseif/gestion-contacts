package com.genesis.gestioncontacts.dto.mapper;

import com.genesis.gestioncontacts.dto.EntrepriseDto;
import com.genesis.gestioncontacts.model.Entreprise;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class EntrepriseDtoMapper implements Function<Entreprise, EntrepriseDto> {

    @Override
    public EntrepriseDto apply(Entreprise entreprise) {
        EntrepriseDto entrepriseDto = new EntrepriseDto();
        entrepriseDto.setId(entreprise.getId());
        entrepriseDto.setTva(entreprise.getTva());
        entrepriseDto.setAdresse(entreprise.getAdresse());
        entrepriseDto.setDateCreation(entreprise.getDateCreation());
        entrepriseDto.setContacts(entreprise.getContacts());
        return  entrepriseDto;
    }
}

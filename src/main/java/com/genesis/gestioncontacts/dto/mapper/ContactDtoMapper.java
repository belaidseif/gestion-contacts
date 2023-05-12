package com.genesis.gestioncontacts.dto.mapper;

import com.genesis.gestioncontacts.dto.ContactDto;

import com.genesis.gestioncontacts.model.Contact;
import org.springframework.stereotype.Component;


import java.util.function.Function;

@Component
public class ContactDtoMapper implements Function<Contact, ContactDto> {

    @Override
    public ContactDto apply(Contact contact) {
        ContactDto contactDto = new ContactDto();
        contactDto.setId(contact.getId());
        contactDto.setNom(contact.getNom());
        contactDto.setPrenom(contact.getPrenom());
        contactDto.setAdresse(contact.getAdresse());
        contactDto.setTva(contact.getTva());
        contactDto.setType(contact.getType());
        return contactDto;
    }
}

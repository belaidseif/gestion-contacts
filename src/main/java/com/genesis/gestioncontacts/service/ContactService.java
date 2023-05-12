package com.genesis.gestioncontacts.service;

import com.genesis.gestioncontacts.dto.ContactDto;
import com.genesis.gestioncontacts.dto.request.AddContactToEntrepriseRequestDto;
import com.genesis.gestioncontacts.dto.request.ContactEditRequestDto;
import com.genesis.gestioncontacts.dto.request.ContactRequestDto;

import java.util.List;

public interface ContactService {
    List<ContactDto> getAllContacts();

    ContactDto addNewContact(ContactRequestDto contactRequestDto);

    ContactDto editContact(Long id, ContactEditRequestDto contactEditRequestDto);

    void deleteContact(Long id);

    ContactDto addContactToEnterprise(AddContactToEntrepriseRequestDto addContactToEntrepriseRequestDto);
}

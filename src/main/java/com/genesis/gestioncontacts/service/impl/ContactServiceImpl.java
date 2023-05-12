package com.genesis.gestioncontacts.service.impl;

import com.genesis.gestioncontacts.dto.ContactDto;
import com.genesis.gestioncontacts.dto.mapper.ContactDtoMapper;
import com.genesis.gestioncontacts.dto.request.AddContactToEntrepriseRequestDto;
import com.genesis.gestioncontacts.dto.request.ContactEditRequestDto;
import com.genesis.gestioncontacts.dto.request.ContactRequestDto;
import com.genesis.gestioncontacts.exception.ContactException;
import com.genesis.gestioncontacts.exception.EntrepriseException;
import com.genesis.gestioncontacts.model.Contact;
import com.genesis.gestioncontacts.model.ContactType;
import com.genesis.gestioncontacts.model.Entreprise;
import com.genesis.gestioncontacts.repository.ContactRepository;
import com.genesis.gestioncontacts.repository.EntrepriseRepository;
import com.genesis.gestioncontacts.service.ContactService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ContactDtoMapper mapper;
    private final EntrepriseRepository entrepriseRepository;
    public ContactServiceImpl(ContactRepository contactRepository, ContactDtoMapper mapper, EntrepriseRepository entrepriseRepository) {
        this.contactRepository = contactRepository;
        this.mapper = mapper;
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public List<ContactDto> getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();

        return contacts.stream().map(mapper).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ContactDto addNewContact(ContactRequestDto contactRequestDto) {
        Optional<Entreprise> entrepriseOpt = entrepriseRepository.findById(contactRequestDto.getEntrepriseId());
        Entreprise entreprise = entrepriseOpt.orElseThrow(() -> new EntrepriseException.EntrepriseNotFound("Entreprise non trouvée"));
        if (contactRequestDto.getType().equals(ContactType.FREELANCE) && contactRequestDto.getTva() == null)
            throw new ContactException.ContactMustHaveTvaNumber("contact doit avoir un numéro de TVA s'il est freelance");
        Contact contact = new Contact(
                contactRequestDto.getNom(),
                contactRequestDto.getPrenom(),
                contactRequestDto.getAdresse(),
                contactRequestDto.getTva(),
                contactRequestDto.getType(),
                entreprise
        );

        Contact savedContact = contactRepository.save(contact);
        return Optional.of(savedContact).map(mapper).get();
    }

    @Override
    public ContactDto editContact(Long id, ContactEditRequestDto contactEditRequestDto) {
        if (contactEditRequestDto.getType().equals(ContactType.FREELANCE) && contactEditRequestDto.getTva() == null)
            throw new ContactException.ContactMustHaveTvaNumber("contact doit avoir un numéro de TVA s'il est freelance");

        Contact contact = getContactById(id);


        contact.setNom(contactEditRequestDto.getNom());
        contact.setPrenom(contactEditRequestDto.getPrenom());
        contact.setAdresse(contactEditRequestDto.getAdresse());
        contact.setTva(contactEditRequestDto.getTva());
        contact.setType(contactEditRequestDto.getType());
        Contact updatedContact = contactRepository.save(contact);
        return Optional.of(updatedContact).map(mapper).get();
    }

    @Override
    public void deleteContact(Long id) {
        Contact contact = getContactById(id);
        contactRepository.delete(contact);
    }

    @Override
    public ContactDto addContactToEnterprise(AddContactToEntrepriseRequestDto addContactToEntrepriseRequestDto) {
        Optional<Entreprise> entrepriseOpt = entrepriseRepository.findById(addContactToEntrepriseRequestDto.getEntrepriseId());
        Entreprise entreprise = entrepriseOpt.orElseThrow(() -> new EntrepriseException.EntrepriseNotFound("Entreprise non trouvée"));
        Contact contact = getContactById(addContactToEntrepriseRequestDto.getContactId());

       if(contact.getEntreprises().contains(entreprise))
           throw new ContactException.ContactHasAlreadyThisEnterprise("Cette entreprise contient déjà ce contact");

        contact.addEnterprise(entreprise);
        Contact savedContact = contactRepository.save(contact);
        return Optional.of(savedContact).map(mapper).get();
    }



    private Contact getContactById(Long id){
        Optional<Contact> contactOpt = contactRepository.findById(id);
        return contactOpt.orElseThrow(() -> new ContactException.ContactNotFound("contact non trouvé"));
    }
}

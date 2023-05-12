package com.genesis.gestioncontacts.controller;

import com.genesis.gestioncontacts.dto.ContactDto;

import com.genesis.gestioncontacts.dto.request.AddContactToEntrepriseRequestDto;
import com.genesis.gestioncontacts.dto.request.ContactEditRequestDto;
import com.genesis.gestioncontacts.dto.request.ContactRequestDto;

import com.genesis.gestioncontacts.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/contacts")
@Tag(name = "contact api")
public class ContactController {


    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("all")
    @Operation(description = "get all contacts")
    public ResponseEntity<List<ContactDto>> listeContacts(){
        List<ContactDto> contacts = contactService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }

    @PostMapping("add")
    @Operation(description = "add new contact")
    public ResponseEntity<ContactDto> ajouterContact(
            @RequestBody @Valid ContactRequestDto contactRequestDto){

        ContactDto contactDto =  contactService.addNewContact(contactRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(contactDto);
    }

    @PutMapping("edit/{id}")
    @Operation(description = "edit existing contact")
    public ResponseEntity<ContactDto> modifierContact(
            @PathVariable @Valid Long id,
            @RequestBody @Valid ContactEditRequestDto contactEditRequestDto){

        ContactDto contactDto= contactService.editContact(id,contactEditRequestDto);

        return ResponseEntity.ok(contactDto);
    }

    @DeleteMapping("delete/{id}")
    @Operation(description = "delete existing contact")
    public ResponseEntity supprimerContact(@PathVariable @Valid Long id){
        contactService.deleteContact(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("add-contact-to-enterprise")
    @Operation(description = "add contact to enterprise")
    public ResponseEntity<ContactDto> ajouterContactAEntreprise(
            @RequestBody @Valid AddContactToEntrepriseRequestDto addContactToEntrepriseRequestDto){
       ContactDto contactDto = contactService.addContactToEnterprise(addContactToEntrepriseRequestDto);
       return ResponseEntity.status(HttpStatus.CREATED).body(contactDto);
    }
}

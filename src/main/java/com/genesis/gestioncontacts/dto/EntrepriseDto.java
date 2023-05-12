package com.genesis.gestioncontacts.dto;

import com.genesis.gestioncontacts.model.Contact;
import jakarta.validation.constraints.NotNull;


import java.time.ZonedDateTime;
import java.util.Set;

public class EntrepriseDto {

    @NotNull
    private Long id;

    @NotNull
    private Integer tva;

    @NotNull
    private String adresse;

    @NotNull
    private ZonedDateTime dateCreation;

    @NotNull
    private Set<Contact> contacts;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTva() {
        return tva;
    }

    public void setTva(Integer tva) {
        this.tva = tva;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public ZonedDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(ZonedDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }
}

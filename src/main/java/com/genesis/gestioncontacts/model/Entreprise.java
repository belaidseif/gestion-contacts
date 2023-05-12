package com.genesis.gestioncontacts.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ENTREPRISES")

public class Entreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TVA",unique = true, nullable = false)
    private Integer tva;



    @Column(name = "ADRESSE",nullable = false)
    private String adresse;

    @Column(name = "DATE_CREATION")
    private ZonedDateTime dateCreation;

    @ManyToMany(mappedBy = "entreprises")

    private Set<Contact> contacts;


    public Entreprise() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entreprise that = (Entreprise) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Entreprise(Integer tva, String adresse) {
        this.tva = tva;
        this.adresse = adresse;
        dateCreation = ZonedDateTime.now();
        contacts = new HashSet<>();
    }

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
